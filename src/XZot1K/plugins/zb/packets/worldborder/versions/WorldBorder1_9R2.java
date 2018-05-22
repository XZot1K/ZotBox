package XZot1K.plugins.zb.packets.worldborder.versions;

import XZot1K.plugins.zb.packets.worldborder.WorldBorder;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldBorder;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldBorder1_9R2 implements WorldBorder
{
    @Override
    public void sendWorldBorder(Player player, Location centerPoint, double borderSize, double damageAmount,
                                double damageBuffer, int warningDistance, int warningTime)
    {
        net.minecraft.server.v1_9_R2.WorldBorder wb = new net.minecraft.server.v1_9_R2.WorldBorder();
        wb.setSize(borderSize);
        wb.setCenter(centerPoint.getX(), centerPoint.getZ());
        wb.setWarningDistance(warningDistance);
        wb.setWarningTime(warningTime);
        wb.setDamageAmount(damageAmount);
        wb.setDamageBuffer(damageBuffer);
        PacketPlayOutWorldBorder packet1 = new PacketPlayOutWorldBorder(wb, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet1);
        PacketPlayOutWorldBorder packet2 = new PacketPlayOutWorldBorder(wb, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet2);
        PacketPlayOutWorldBorder packet3 = new PacketPlayOutWorldBorder(wb, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet3);
    }
}
