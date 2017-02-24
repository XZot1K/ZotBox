package XZot1K.plugins.zl.packets.bossbar;

import XZot1K.plugins.zl.ZotLib;
import net.minecraft.server.v1_8_R2.EntityWither;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R2.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class BossBar1_8R2 implements OlderBossBar
{

    private ZotLib plugin = ZotLib.getInstance();
    private String text;
    private EntityWither wither;
    private Player player;
    private BukkitTask task;

    public BossBar1_8R2(Player player, String text)
    {
        setPlayer(player);
        setText(text);
        task = plugin.getServer().getScheduler().runTaskTimer(plugin, this::update, 0, 0);
    }

    private void update()
    {
        if (player != null && player.isOnline())
        {
            hide();
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
            setWither(new EntityWither(world));
            Location location = player.getLocation().add(player.getLocation().getDirection().multiply(50));
            getWither().setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
            getWither().setCustomName(plugin.getGeneralLibrary().color(getText()));
            getWither().setInvisible(true);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(getWither());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            return;
        }

        task.cancel();
    }

    public void show()
    {
        if (task == null)
        {
            task = plugin.getServer().getScheduler().runTaskTimer(plugin, this::update, 0, 20);
        }
    }

    public void hide()
    {
        if (getWither() != null)
        {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(getWither().getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void stop()
    {
        if (task != null)
        {
            task.cancel();
        }
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public double getProgress()
    {
        return getWither().getHealth();
    }

    public void setProgress(double progress)
    {
        getWither().setHealth((float) progress);
    }

    public EntityWither getWither()
    {
        return wither;
    }

    public void setWither(EntityWither wither)
    {
        this.wither = wither;
    }

    private void setPlayer(Player player)
    {
        this.player = player;
    }
}
