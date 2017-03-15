package XZot1K.plugins.zb.utils;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownSet
{
    private ZotBox plugin = ZotBox.getInstance();
    private HashMap<UUID, Long> coolDowns;
    private int duration;

    /**
     * Creates a new CooldownSet where you can create unlimited cooldowns based on 1 duration.
     *
     * @param duration The duration you would like this CooldownSet to contain.
     */
    public CooldownSet(int duration)
    {
        setCoolDowns(new HashMap<>());
        setDuration(duration);
    }

    /**
     * Returns the time remaining on a player's cooldown.
     *
     * @param player The player you would like to check.
     * @return The time remaining on a player's cooldown.
     */
    public long durationLeft(Player player)
    {
        if (getCooldowns().containsKey(player.getUniqueId()))
        {
            return ((getCooldowns().get(player.getUniqueId()) / 1000) + getDuration()) - (System.currentTimeMillis() / 1000);
        }

        return 0;
    }

    /**
     * Returns if the player is on cooldown or not.
     *
     * @param player The player you would like to check.
     * @return If the player is on cooldown or not.
     */
    public boolean onCooldown(Player player)
    {
        return durationLeft(player) > 0;
    }

    /**
     * Creates a cooldown for the specified player through System time.
     *
     * @param player Player you want to put on cooldown.
     */
    public void createCooldDown(Player player)
    {
        getCooldowns().put(player.getUniqueId(), System.currentTimeMillis());
    }

    // Getters & Setters
    public HashMap<UUID, Long> getCooldowns()
    {
        return coolDowns;
    }

    private void setCoolDowns(HashMap<UUID, Long> coolDowns)
    {
        this.coolDowns = coolDowns;
    }

    /**
     * Gets the fed duration of the CooldownSet.
     *
     * @return The fed duration of the CooldownSet.
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * Sets the fed duration of the CooldownSet.
     *
     * @param duration The duration you want to switch it to.
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

}
