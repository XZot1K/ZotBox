package XZot1K.plugins.zl.packets.holograms;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface Hologram
{

    Hologram showPlayer(Player p, int displayTime);

    Hologram showAll(int displayTime);

    Hologram showPlayer(Player p);

    Hologram hidePlayer(Player p);

    Hologram showAll();

    Hologram hideAll();

    Hologram create();

    Location getLocation();

    Hologram setLocation(Location location);

    List<String> getLines();

    Hologram setLines(List<String> lines);

    double getLineSpread();

    Hologram setLineSpread(double lineSpread);

    String getId();

    void setId(String id);

}
