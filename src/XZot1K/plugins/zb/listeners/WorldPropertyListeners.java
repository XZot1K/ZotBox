package XZot1K.plugins.zb.listeners;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.utils.worldmanagment.WorldProperty;
import XZot1K.plugins.zb.utils.worldmanagment.WorldSnapshot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;

public class WorldPropertyListeners implements Listener
{
    private ZotBox pluginInstance;

    public WorldPropertyListeners()
    {
        pluginInstance = ZotBox.getInstance();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getBlock().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_BREAK))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(pluginInstance.getGeneralLibrary().color(pluginInstance.getConfig().getString("world-disallow-message")
                    .replace("{world}", e.getBlock().getWorld().getName())));
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getBlock().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_BUILD))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(pluginInstance.getGeneralLibrary().color(pluginInstance.getConfig().getString("world-disallow-message")
                    .replace("{world}", e.getBlock().getWorld().getName())));
        }
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getSpawner().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.SPAWNER_SPAWNING))
            e.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getItemDrop().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_ITEM_DROPPING))
            e.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getLocation().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.MOB_SPAWNING))
            e.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getLocation().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.EXPLOSIVE_BLOCK_DAMAGE))
        {
            e.blockList().clear();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if (e.getEntity() instanceof Player)
        {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL)
            {
                WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getEntity().getWorld().getName());
                if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_FALL_DAMAGE))
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getEntity().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_HUNGER))
        {
            e.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e)
    {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
        {
            WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getDamager().getWorld().getName());
            if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.PLAYER_VS_PLAYER))
            {
                e.setCancelled(true);
                e.getDamager().sendMessage(pluginInstance.getGeneralLibrary().color(pluginInstance.getConfig().getString("world-disallow-message")
                        .replace("{world}", e.getDamager().getWorld().getName())));
            }
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e)
    {
        WorldSnapshot worldSnapshot = pluginInstance.getWorldManager().getWorldSnapshot(e.getBlock().getWorld().getName());
        if (worldSnapshot != null && !worldSnapshot.getWorldProperty(WorldProperty.LEAF_DECAY))
            e.setCancelled(true);
    }

}
