package XZot1K.plugins.zb.utils.worldmanagment;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldManager
{

    private ZotBox pluginInstance;
    private HashMap<String, WorldSnapshot> worldSnapshots;

    /*
     commented lines are disabled until full WorldManager API release.
      */

    public WorldManager(ZotBox pluginInstance)
    {
        this.pluginInstance = pluginInstance;
        worldSnapshots = new HashMap<>();
    }

    private void loadWorldData()
    {
        File directory = new File(pluginInstance.getDataFolder(), "/worlds");
        directory.mkdirs();

        File[] directoryFiles = directory.listFiles();
        if (directoryFiles == null) return;

        for (int i = -1; ++i < directoryFiles.length; )
        {
            File file = directoryFiles[i];
            if (file != null && file.getName().toLowerCase().endsWith(".yml"))
            {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                WorldType worldType = WorldType.getByName(yaml.getString("world-type").toUpperCase()
                        .replace(" ", "_").replace("-", "_"));
                World.Environment environment = World.Environment.valueOf(yaml.getString("environment").toUpperCase()
                        .replace(" ", "_").replace("-", "_"));

                WorldSnapshot worldSnap = new WorldSnapshot(yaml.getString("name"), yaml.getString("generator"),
                        yaml.getString("generator-settings"), yaml.getBoolean("generate-structures"),
                        environment, worldType, yaml.getLong("seed"));
                worldSnap.setLoaded(yaml.getBoolean("loaded"));

                List<String> properties = new ArrayList<>(yaml.getConfigurationSection("properties").getKeys(false));
                for (int j = -1; ++j < properties.size(); )
                {
                    try
                    {
                        String propertyId = properties.get(j);
                        worldSnap.updateWorldProperty(WorldProperty.valueOf(propertyId.toUpperCase()
                                        .replace(" ", "_").replace("-", "_")),
                                yaml.getBoolean("properties." + propertyId));
                    } catch (Exception ignored) {}
                }

                pluginInstance.getGeneralLibrary().sendConsoleMessage("&aThe world &e" + worldSnap.getWorldName() + " &awas loaded. ");
                getWorldSnapshots().put(worldSnap.getWorldName(), worldSnap);

                if (worldSnap.isLoaded() && !isWorldLoaded(worldSnap.getWorldName()))
                    buildWorld(worldSnap.getWorldName(), worldSnap.getGenerator(), worldSnap.canGenerateStructures(),
                            worldSnap.getEnvironment(), worldSnap.getSeed(), worldSnap.getWorldType(), worldSnap.getGeneratorSettings());
            }
        }
    }

    public void saveWorldData(boolean saveAsynchronously)
    {
        pluginInstance.getGeneralLibrary().sendConsoleMessage("&a" + getWorldSnapshots().toString());
        List<String> worldNames = new ArrayList<>(getWorldSnapshots().keySet());
        for (int i = -1; ++i < worldNames.size(); )
        {
            String worldName = worldNames.get(i);
            WorldSnapshot worldSnapshot = getWorldSnapshot(worldName);
            if (worldSnapshot != null) worldSnapshot.save(saveAsynchronously);
        }
    }

    public void syncWorldData()
    {
        long startTime = System.currentTimeMillis();
        pluginInstance.getGeneralLibrary().sendConsoleMessage("&aSyncing accessible world data...");
        loadWorldData();

        List<World> worldList = new ArrayList<>(pluginInstance.getServer().getWorlds());
        for (int i = -1; ++i < worldList.size(); )
        {
            World world = worldList.get(i);
            WorldSnapshot worldSnapshot = getWorldSnapshot(world.getName());
            if (worldSnapshot == null)
            {
                WorldSnapshot newWorldSnapshot = new WorldSnapshot(world.getName(), "",
                        "", world.canGenerateStructures(), world.getEnvironment(), world.getWorldType(), world.getSeed());
                newWorldSnapshot.save(true);
                getWorldSnapshots().put(world.getName(), newWorldSnapshot);
                pluginInstance.getGeneralLibrary().sendConsoleMessage("&aCreated world snapshot for the world &e" + world.getName() + "&a. ");
            }
        }

        List<String> worldNames = new ArrayList<>(getWorldSnapshots().keySet());
        for (int i = -1; ++i < worldNames.size(); )
        {
            String worldName = worldNames.get(i);
            WorldSnapshot worldSnapshot = getWorldSnapshot(worldName);
            if (worldSnapshot != null && isWorldLoaded(worldName) && !worldSnapshot.isLoaded())
            {
                unloadWorld(worldName, true);
                pluginInstance.getGeneralLibrary().sendConsoleMessage("&aUnloaded the world &e" + worldName + "&a.");
            } else if (worldSnapshot != null && !isWorldLoaded(worldName) && worldSnapshot.isLoaded())
            {
                buildWorld(worldName, null, true, World.Environment.NORMAL, 0, WorldType.NORMAL, null);
                pluginInstance.getGeneralLibrary().sendConsoleMessage("&aRe-Loaded the world &e" + worldName + "&a.");
            }
        }

        long endTime = System.currentTimeMillis();
        pluginInstance.getGeneralLibrary().sendConsoleMessage("&aFinished syncing world data! &a(Took &e" + (endTime - startTime) + "ms&a)");
    }

    public boolean isWorldLoaded(String worldName)
    {
        return getWorldByName(worldName) != null;
    }

    public WorldSnapshot getWorldSnapshot(String worldName)
    {
        if (!getWorldSnapshots().isEmpty() && getWorldSnapshots().containsKey(worldName))
            return getWorldSnapshots().get(worldName);
        return null;
    }

    public void clearWorldSnapshot(String worldName)
    {
        if (!getWorldSnapshots().isEmpty()) getWorldSnapshots().remove(worldName);
    }

    public void buildWorld(String worldName, String generator, boolean generateStructures,
                           World.Environment environment, long seed, WorldType worldType, String generatorSettings)
    {
        WorldCreator worldCreator = new WorldCreator(worldName);
        if (generator != null) worldCreator.generator(generator);
        worldCreator.generateStructures(generateStructures);
        if (environment != null) worldCreator.environment(environment);
        if (seed != 0) worldCreator.seed(seed);
        if (worldType != null) worldCreator.type(worldType);
        if (generatorSettings != null) worldCreator.generatorSettings(generatorSettings);

        World world = pluginInstance.getServer().createWorld(worldCreator);
        WorldSnapshot worldSnapshot = new WorldSnapshot(worldName, generator, generatorSettings, generateStructures, environment, worldType, seed);
        worldSnapshot.save(true);
        getWorldSnapshots().put(world.getName(), worldSnapshot);
    }

    public World getWorldByName(String worldName)
    {
        return pluginInstance.getServer().getWorld(worldName);
    }

    public void unloadWorld(String worldName, boolean save) { pluginInstance.getServer().unloadWorld(worldName, save); }

    // getters and setters
    public HashMap<String, WorldSnapshot> getWorldSnapshots()
    {
        return worldSnapshots;
    }
}
