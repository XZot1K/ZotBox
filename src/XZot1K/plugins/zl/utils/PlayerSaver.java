package XZot1K.plugins.zl.utils;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class PlayerSaver
{

    private ZotLib plugin = Manager.getPlugin();
    private SerializableLocation location;
    private ItemStack[] inventoryContents, armorContents;
    private Collection<PotionEffect> potionEffects;
    private boolean flying, allowFlight;
    private GameMode gameMode;
    private int level, experience, hunger;
    private double health;
    public PlayerSaver(Player player)
    {
        setLocation(player.getLocation());
        setArmorContents(player.getInventory().getArmorContents());
        setInventoryContents(player.getInventory().getContents());
        setPotionEffects(player.getActivePotionEffects());
        setAllowFlight(player.getAllowFlight());
        setFlying(player.isFlying());
        setGameMode(player.getGameMode());
        setLevel(player.getLevel());
        setExperience(player.getExpToLevel());
        setHealth(player.getHealth());
        setHunger(player.getFoodLevel());
    }

    public void restoreToPlayer(Player player, boolean restoreLocation, boolean restoreFlight, boolean restoreGameMode, boolean restoreInventory,
                                boolean restoreExperience, boolean restoreHealthAndFood, boolean restorePotionEffects, boolean notifyConsole)
    {
        if (restoreFlight)
        {
            player.setAllowFlight(isAllowFlight());
            player.setFlying(isFlying());
        }

        if (restoreGameMode)
        {
            player.setGameMode(getGameMode());
        }

        if (restoreInventory)
        {
            player.getInventory().setArmorContents(getArmorContents());
            player.getInventory().setContents(getInventoryContents());
        }

        if (restoreExperience)
        {
            player.setLevel(getLevel());
            player.setExp(getExperience());

        }

        if (restoreHealthAndFood)
        {
            player.setHealth(getHealth());
            player.setFoodLevel(getHunger());
        }

        if (restorePotionEffects)
        {
            for (PotionEffect potionEffect : getPotionEffects())
            {
                player.addPotionEffect(potionEffect);
            }
        }

        if (restoreLocation)
        {
            player.teleport(getLocation());
        }

        if (notifyConsole)
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&e" + player.getName() + "'s &aplayer data has been successfully set to the player data in a &ePlayerSaver&a!");
        }
    }

    public ItemStack[] getInventoryContents()
    {
        return inventoryContents;
    }

    public void setInventoryContents(ItemStack[] inventoryContents)
    {
        this.inventoryContents = inventoryContents;
    }

    public ItemStack[] getArmorContents()
    {
        return armorContents;
    }

    public void setArmorContents(ItemStack[] armorContents)
    {
        this.armorContents = armorContents;
    }

    public Location getLocation()
    {
        return location.asBukkitLocation();
    }

    public void setLocation(Location location)
    {
        this.location = new SerializableLocation(location);
    }

    public Collection<PotionEffect> getPotionEffects()
    {
        return potionEffects;
    }

    public void setPotionEffects(Collection<PotionEffect> potionEffects)
    {
        this.potionEffects = potionEffects;
    }

    public boolean isAllowFlight()
    {
        return allowFlight;
    }

    public void setAllowFlight(boolean allowFlight)
    {
        this.allowFlight = allowFlight;
    }

    public boolean isFlying()
    {
        return flying;
    }

    public void setFlying(boolean flying)
    {
        this.flying = flying;
    }

    public GameMode getGameMode()
    {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode)
    {
        this.gameMode = gameMode;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getHunger()
    {
        return hunger;
    }

    public void setHunger(int hunger)
    {
        this.hunger = hunger;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

}
