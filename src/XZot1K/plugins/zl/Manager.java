package XZot1K.plugins.zl;

public class Manager
{

    private static ZotLib plugin;

    public Manager(ZotLib pl)
    {
        setPlugin(pl);
    }

    public static ZotLib getPlugin()
    {
        return plugin;
    }

    private static void setPlugin(ZotLib plugin)
    {
        Manager.plugin = plugin;
    }

}
