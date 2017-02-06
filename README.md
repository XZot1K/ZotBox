# How to use ZotLib

In order to properly use ZotLib you will need to be able to retrieve the Lib's instance. You can do this by following these instructions:

1. You need to download the ZotLib dependency and add it to your plugin's dependencies.  
2. You need to make sure you are able to grab the ZotLib instance from your Main class file. Example:  

'''import XZot1K.plugins.zl.ZotLib;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class Main extends JavaPlugin
{

    private ZotLib zotLib;

    @Override
    public void onEnable()
    {
        if(!isZotLibInstalled())
        {
            getLogger().log(Level.WARNING, "Disabling due to ZotLib not being installed...");
            getServer().getPluginManager().disablePlugin(this);
        }

        getZotLib().getGeneralLibrary().sendConsoleMessage(this, "&aZotLib was found and has been successfully hooked into!");
    }

    private boolean isZotLibInstalled()
    {
        ZotLib zotLib = (ZotLib) getServer().getPluginManager().getPlugin("ZotLib");
        if(zotLib != null)
        {
            setZotLib(zotLib);
        }
        return false;
    }

    public ZotLib getZotLib()
    {
        return zotLib;
    }

    private void setZotLib(ZotLib zotLib)
    {
        this.zotLib = zotLib;
    }

}'''

3. Once 1 and 2 are completed you will need to modify your depends, or softdepends, within your plugin.yml (This step is optional, but never hurts to make sure ZotLib is installed).  
4. You are good to go now you can call the getZotLib() method from your Main class and begin your adventure within ZotLib!
