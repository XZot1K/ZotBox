package XZot1K.plugins.zb.utils.worldmanagment;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

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
        // if (pluginInstance.getConfig().getBoolean("use-world-manager")) loadWorldData();
    }

    private void loadWorldData()
    {
        File directory = new File(pluginInstance.getDataFolder(), "/worlds");
        if (!directory.mkdirs()) return;

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

                getWorldSnapshots().put(worldSnap.getWorldName(), worldSnap);

                if (worldSnap.isLoaded() && !isWorldLoaded(worldSnap.getWorldName()))
                    buildWorld(worldSnap.getWorldName(), worldSnap.getGenerator(), worldSnap.canGenerateStructures(),
                            worldSnap.getEnvironment(), worldSnap.getSeed(), worldSnap.getWorldType(), worldSnap.getGeneratorSettings());
            }
        }
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
        worldCreator.seed(seed);
        if (worldType != null) worldCreator.type(worldType);
        if (generatorSettings != null) worldCreator.generatorSettings(generatorSettings);
        worldCreator.createWorld();
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
