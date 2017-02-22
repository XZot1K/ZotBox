package XZot1K.plugins.zl.utils.cooldowns;

import XZot1K.plugins.zl.ZotLib;

import java.util.ArrayList;

public class CoolDownManager
{
    private ZotLib plugin = ZotLib.getInstance();
    private ArrayList<CoolDown> coolDowns;

    public CoolDownManager()
    {
        setCoolDowns(new ArrayList<>());
    }

    public ArrayList<CoolDown> getCoolDowns()
    {
        return coolDowns;
    }

    private void setCoolDowns(ArrayList<CoolDown> coolDowns)
    {
        this.coolDowns = coolDowns;
    }

    public boolean registerCoolDown(CoolDown coolDown, boolean notify)
    {
        if (!getCoolDowns().contains(coolDown))
        {
            getCoolDowns().add(coolDown);
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aThe cool-down with the id &e" + coolDown.getId() +
                        " &ahas been successfully registered.");
            }

            return true;
        }

        if (notify)
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&cTried to register the cool-down with the id &e" + coolDown.getId()
                    + " &c, but it is already registered.");
        }

        return false;
    }

    public boolean unRegisterCoolDown(CoolDown coolDown, boolean notify)
    {
        if (getCoolDowns().contains(coolDown))
        {
            getCoolDowns().remove(coolDown);
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aThe cool-down with the id &e" + coolDown.getId() +
                        " &ahas been successfully un-registered.");
            }

            return true;
        }

        if (notify)
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&cTried to un-register the cool-down with the id &e" + coolDown.getId() +
                    " &c, but it is not registered.");
        }

        return false;
    }

    public CoolDown getCoolDown(String id)
    {
        for (CoolDown coolDown : getCoolDowns())
        {
            if (coolDown.getId().equalsIgnoreCase(id))
            {
                return coolDown;
            }
        }
        return null;
    }

    public boolean doesCoolDownWithIdExist(String id)
    {
        for (CoolDown coolDown : getCoolDowns())
        {
            if (coolDown.getId().equalsIgnoreCase(id))
            {
                return true;
            }
        }
        return false;
    }

    public CoolDown createCooldDown(String id, int duration, boolean notify)
    {
        if (!doesCoolDownWithIdExist(id))
        {
            CoolDown coolDown = new CoolDown(id, duration, notify);
            registerCoolDown(coolDown, notify);
            return coolDown;
        }

        if (notify)
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&cUnable to create a new cool-down with the id &e" + id + " &c, " +
                    "due to a cool-down already existing with the same id.");
        }
        return null;
    }

}
