package XZot1K.plugins.zb.packets.ewalker;

import net.minecraft.server.v1_10_R1.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

public class EWalker1_10R1 implements EWalker
{
    @Override
    public void walkEntity(LivingEntity livingEntity, Location location, double speed)
    {
        EntityInsentient entityInsentient = (EntityInsentient) ((CraftLivingEntity) livingEntity).getHandle();
        entityInsentient.getNavigation().a(location.getX(), location.getY(), location.getZ(), speed);
    }
}
