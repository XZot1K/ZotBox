package XZot1K.plugins.zb.packets.ewalker;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public interface EWalker
{
    void walkEntity(LivingEntity livingEntity, Location location, double speed);
}
