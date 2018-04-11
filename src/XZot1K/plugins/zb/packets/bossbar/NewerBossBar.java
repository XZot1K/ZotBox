package XZot1K.plugins.zb.packets.bossbar;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class NewerBossBar
{

    private ZotBox plugin = ZotBox.getInstance();
    private BossBar bossBar;
    private String text;
    private BarColor barColor;
    private BarStyle barStyle;
    private BarFlag barFlag;

    public NewerBossBar(String text, BarColor barColor, BarStyle barStyle, BarFlag barFlag)
    {
        setText(text);
        setBarColor(barColor);
        setBarStyle(barStyle);
        setBarFlag(barFlag);
    }

    public NewerBossBar create()
    {
        if (getBarFlag() == null)
        {
            BossBar bar = plugin.getServer().createBossBar(plugin.getGeneralLibrary().color(text),
                    getBarColor(), getBarStyle());
            setBossBar(bar);
        } else
        {
            BossBar bar = plugin.getServer().createBossBar(plugin.getGeneralLibrary().color(text),
                    getBarColor(), getBarStyle(), getBarFlag());
            setBossBar(bar);
        }

        return this;
    }

    @SuppressWarnings("deprecation")
	public NewerBossBar show()
    {
        switch (plugin.getServerVersion())
        {
            case "v1_9_R2":
                getBossBar().show();
                break;
            case "v1_9_R1":
                getBossBar().show();
                break;
            default:
                getBossBar().setVisible(true);
                break;
        }

        return this;
    }

    @SuppressWarnings("deprecation")
	public NewerBossBar hide()
    {
        switch (plugin.getServerVersion())
        {
            case "v1_9_R2":
                getBossBar().hide();
                break;
            case "v1_9_R1":
                getBossBar().hide();
                break;
            default:
                getBossBar().setVisible(false);
                break;
        }

        return this;
    }

    public NewerBossBar addPlayer(Player player)
    {
        if (getBossBar() != null)
        {
            getBossBar().addPlayer(player);
        }

        return this;
    }

    public NewerBossBar removePlayer(Player player)
    {
        if (getBossBar() != null)
        {
            getBossBar().removePlayer(player);
        }

        return this;
    }

    public double getProgress()
    {
        if (getBossBar() != null)
        {
            return getBossBar().getProgress();
        }

        return 0.0;
    }

    public NewerBossBar setProgress(double progress)
    {
        if (getBossBar() != null)
        {
            getBossBar().setProgress(progress);
        }

        return this;
    }

    public BossBar getBossBar()
    {
        return bossBar;
    }

    private NewerBossBar setBossBar(BossBar bossBar)
    {
        this.bossBar = bossBar;
        return this;
    }

    public BarFlag getBarFlag()
    {
        return barFlag;
    }

    public NewerBossBar setBarFlag(BarFlag barFlag)
    {
        this.barFlag = barFlag;
        return this;
    }

    public String getText()
    {
        return text;
    }

    public NewerBossBar setText(String text)
    {
        this.text = text;
        return this;
    }

    public BarColor getBarColor()
    {
        return barColor;
    }

    public NewerBossBar setBarColor(BarColor barColor)
    {
        this.barColor = barColor;
        return this;
    }

    public BarStyle getBarStyle()
    {
        return barStyle;
    }

    public NewerBossBar setBarStyle(BarStyle barStyle)
    {
        this.barStyle = barStyle;
        return this;
    }

}
