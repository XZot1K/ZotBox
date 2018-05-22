package XZot1K.plugins.zb.utils.worldmanagment;

import org.bukkit.World;
import org.bukkit.WorldType;

public class WorldSnapshot
{

    private String worldName, generator, generatorSettings;
    private World.Environment environment;
    private WorldType worldType;
    private long seed;
    private boolean loaded, generateStructures;


    WorldSnapshot(String worldName, String generator, String generatorSettings, boolean generateStructures, World.Environment environment,
                  WorldType worldType, long seed)
    {
        setLoaded(true);
        this.worldName = worldName;
        this.generator = generator;
        this.generatorSettings = generatorSettings;
        this.generateStructures = generateStructures;
        this.environment = environment;
        this.worldType = worldType;
        this.seed = seed;
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
}
