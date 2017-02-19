package XZot1K.plugins.zl.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomFirework
{

    private Firework firework;

    public CustomFirework(Location location)
    {
        setFirework(location.getWorld().spawn(location, Firework.class));
    }

    public Firework getFirework()
    {
        return firework;
    }

    public void setFirework(Firework firework)
    {
        this.firework = firework;
    }

    public CustomFirework setPower(int power)
    {
        FireworkMeta meta = getFirework().getFireworkMeta();
        meta.setPower(power);
        getFirework().setFireworkMeta(meta);
        return this;
    }

    public CustomFirework addEffect(FireworkEffect effect)
    {
        FireworkMeta meta = getFirework().getFireworkMeta();
        meta.addEffect(effect);
        getFirework().setFireworkMeta(meta);
        return this;
    }

    public CustomFirework addEffects(List<FireworkEffect> effect)
    {
        FireworkMeta meta = getFirework().getFireworkMeta();
        meta.addEffects(effect);
        getFirework().setFireworkMeta(meta);
        return this;
    }

    public CustomFirework addEffects(ArrayList<FireworkEffect> effect)
    {
        FireworkMeta meta = getFirework().getFireworkMeta();
        meta.addEffects(effect);
        getFirework().setFireworkMeta(meta);
        return this;
    }

    public CustomFirework clearEffects()
    {
        FireworkMeta meta = getFirework().getFireworkMeta();
        meta.clearEffects();
        getFirework().setFireworkMeta(meta);
        return this;
    }

    public FireworkEffect.Type getRandomType()
    {
        FireworkEffect.Type[] types = FireworkEffect.Type.values();
        return types[new Random().nextInt(types.length)];
    }

    public Color getRandomColor()
    {
        Color[] colors = {Color.YELLOW, Color.WHITE, Color.TEAL, Color.SILVER, Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA,
                Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED};
        return colors[new Random().nextInt(colors.length)];
    }

}
