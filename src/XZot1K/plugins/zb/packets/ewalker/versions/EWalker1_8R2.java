package XZot1K.plugins.zb.packets.ewalker.versions;

import XZot1K.plugins.zb.packets.ewalker.EWalker;
import net.minecraft.server.v1_8_R2.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

public class EWalker1_8R2 implements EWalker
{
    @Override
    public void walkEntity(LivingEntity livingEntity, Location location, double speed)
    {
        EntityInsentient entityInsentient = (EntityInsentient) ((CraftLivingEntity) livingEntity).getHandle();
        entityInsentient.getNavigation().a(location.getX(), location.getY(), location.getZ(), speed);
    }
}
