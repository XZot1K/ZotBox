package XZot1K.plugins.zb.utils.worldmanagment;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldSnapshot
{
    private ZotBox pluginInstance;
    private String worldName, generator, generatorSettings;
    private World.Environment environment;
    private WorldType worldType;
    private long seed;
    private boolean loaded, generateStructures;
    private HashMap<WorldProperty, Boolean> worldProperties;

    public WorldSnapshot(String worldName, String generator, String generatorSettings, boolean generateStructures, World.Environment environment,
                         WorldType worldType, long seed)
    {
        pluginInstance = ZotBox.getInstance();
        setLoaded(true);
        this.worldName = worldName;
        this.generator = generator;
        this.generatorSettings = generatorSettings;
        this.generateStructures = generateStructures;
        this.environment = environment;
        this.worldType = worldType;
        this.seed = seed;
        setWorldProperties(new HashMap<>());

        for (int i = -1; ++i < WorldProperty.values().length; )
        {
            WorldProperty worldProperty = WorldProperty.values()[i];
            updateWorldProperty(worldProperty, true);
        }
    }

    public void save(boolean saveAsynchronously)
    {
        if (saveAsynchronously)
            pluginInstance.getServer().getScheduler().runTaskAsynchronously(pluginInstance, this::runSave);
        else runSave();
    }

    private void runSave()
    {
        try
        {
            File file = new File(pluginInstance.getDataFolder(), "/worlds/" + getWorldName() + ".yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.set("name", getWorldName());
            yaml.set("loaded", isLoaded());
            yaml.set("environment", getEnvironment().name());
            yaml.set("world-type", getWorldType().name());
            yaml.set("generator", getGenerator());
            yaml.set("generator-settings", getGeneratorSettings());
            yaml.set("generator", getGenerator());
            yaml.set("seed", getSeed());

            yaml.createSection("properties");
            List<WorldProperty> worldProperties = new ArrayList<>(getWorldProperties().keySet());
            for (int i = -1; ++i < worldProperties.size(); )
            {
                WorldProperty worldProperty = worldProperties.get(i);
                yaml.set("properties." + worldProperty.name(), getWorldProperties().get(worldProperty));
            }

            yaml.save(file);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean getWorldProperty(WorldProperty worldProperty)
    {
        if (!getWorldProperties().isEmpty() && getWorldProperties().containsKey(worldProperty))
            return getWorldProperties().get(worldProperty);
        return false;
    }

    public void updateWorldProperty(WorldProperty worldProperty, boolean activeProperty)
    {
        getWorldProperties().put(worldProperty, activeProperty);
    }

    public String getWorldName()
    {
        return worldName;
    }

    public String getGenerator()
    {
        return generator;
    }

    public String getGeneratorSettings()
    {
        return generatorSettings;
    }

    public World.Environment getEnvironment()
    {
        return environment;
    }

    public WorldType getWorldType()
    {
        return worldType;
    }

    public long getSeed()
    {
        return seed;
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    public void setLoaded(boolean loaded)
    {
        this.loaded = loaded;
    }

    public boolean canGenerateStructures()
    {
        return generateStructures;
    }

    private HashMap<WorldProperty, Boolean> getWorldProperties()
    {
        return worldProperties;
    }

    private void setWorldProperties(HashMap<WorldProperty, Boolean> worldProperties)
    {
        this.worldProperties = worldProperties;
    }
}
