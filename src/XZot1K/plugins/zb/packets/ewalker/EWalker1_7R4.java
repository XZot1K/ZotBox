package XZot1K.plugins.zb.packets.ewalker;

import net.minecraft.server.v1_7_R4.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

public class EWalker1_7R4 implements EWalker
{
    @Override
    public void walkEntity(LivingEntity livingEntity, Location location, double speed)
    {
        EntityInsentient entityInsentient = (EntityInsentient) ((CraftLivingEntity) livingEntity).getHandle();
        entityInsentient.getNavigation().a(location.getX(), location.getY(), location.getZ(), speed);
    }
}
