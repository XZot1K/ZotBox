package XZot1K.plugins.zb.packets.bossbar.versions;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.bossbar.OlderBossBar;
import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class BossBar1_8R3 implements OlderBossBar
{

    private ZotBox plugin = ZotBox.getInstance();
    private String text;
    private EntityWither wither;
    private Player player;
    private BukkitTask task;
    private double progress;

    public BossBar1_8R3(Player player, String text)
    {
        setPlayer(player);
        setText(text);
        setProgress(300.0);
        task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, this::update, 0, 0);
    }

    private void update()
    {
        if (player != null && player.isOnline())
        {
            if (getWither() == null) setWither(new EntityWither(((CraftWorld) player.getWorld()).getHandle()));
            Location location = player.getLocation().add(player.getLocation().getDirection().multiply(50));
            getWither().setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
            getWither().setCustomName(plugin.getGeneralLibrary().color(getText()));
            getWither().setInvisible(true);
            getWither().setHealth((float) getProgress());
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(getWither());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        } else task.cancel();
    }

    public void show()
    {
        if (task == null)
            task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, this::update, 0, 20);
    }

    public void hide()
    {
        if (task != null)
        {
            task.cancel();
            task = null;
        }

        if (getWither() != null)
        {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(getWither().getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void stop()
    {
        if (task != null) task.cancel();
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
        return progress;
    }

    public void setProgress(double progress)
    {
        this.progress = progress;
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
