# How to use ZotBox

In order to properly use ZotBox you will need to be able to retrieve the Lib's instance. You can do this by following these instructions:

1. You need to download the ZotBox dependency and add it to your plugin's dependencies.  
2. You need to make sure you are able to grab the ZotBox instance from your Main class file. Example:  

```
import XZot1K.plugins.zl.ZotBox;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class Main extends JavaPlugin
{

    private ZotBox zotBox;

    @Override
    public void onEnable()
    {
        if (!isZotBoxInstalled())
        {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e"
                    + getName() + " &cwas unable to enable, due to &bZot&7Box &cnot being installed."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getZotBox().getGeneralLibrary().sendConsoleMessage(this, "&aZotBox was found and has been successfully hooked into!");
    }

    private boolean isZotBoxInstalled()
    {
        ZotBox zotBox = (ZotBox) getServer().getPluginManager().getPlugin("ZotBox");
        if(zotBox != null)
        {
            setZotBox(zotBox);
            return true;
        }
        return false;
    }

    public ZotBox getZotBox()
    {
        return zotBox;
    }

    private void setZotBox(ZotBox zotBox)
    {
        this.zotBox = zotBox;
    }

}
```

3. Once 1 and 2 are completed you will need to modify your "depends: []", or "softdepends: []", within your plugin.yml (This step is optional, but never hurts to make sure ZotBox is installed).  
4. You are good to go now you can call the getZotBox() method from your Main class and begin your adventure within ZotBox!
