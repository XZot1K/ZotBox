package XZot1K.plugins.zl.utils;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class CustomScoreboard
{

    private ZotLib plugin = ZotLib.getInstance();
    private ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;

    public CustomScoreboard(boolean useMainScoreboard)
    {
        setScoreboardManager(plugin.getServer().getScoreboardManager());

        if (useMainScoreboard)
        {
            setScoreboard(getScoreboardManager().getMainScoreboard());
        } else
        {
            setScoreboard(getScoreboardManager().getNewScoreboard());
        }
    }

    public CustomScoreboard registerObjective(String objectiveName, String objectiveCriteria, String displayName, DisplaySlot displaySlot)
    {
        try
        {
            getScoreboard().getObjective(objectiveName).unregister();
        } catch (Exception ignored)
        {
        }

        Objective objective = getScoreboard().registerNewObjective(objectiveName, objectiveCriteria);
        objective.setDisplayName(plugin.getGeneralLibrary().color(displayName));
        objective.setDisplaySlot(displaySlot);
        return this;
    }

    public CustomScoreboard score(String objectiveName, String scoreName, int value)
    {
        Objective objective = getScoreboard().getObjective(objectiveName);
        if (objective != null)
        {
            Score score = objective.getScore(plugin.getGeneralLibrary().color(scoreName));
            score.setScore(value);
        }

        return this;
    }

    public CustomScoreboard showAllPlayers()
    {
        for (Player player : plugin.getServer().getOnlinePlayers())
        {
            showPlayer(player);
        }

        return this;
    }

    public CustomScoreboard hideAllPlayers()
    {
        for (Player player : plugin.getServer().getOnlinePlayers())
        {
            hidePlayer(player);
        }

        return this;
    }

    public CustomScoreboard showPlayer(Player player)
    {
        player.setScoreboard(getScoreboard());
        return this;
    }

    public CustomScoreboard hidePlayer(Player player)
    {
        player.setScoreboard(getScoreboardManager().getNewScoreboard());
        return this;
    }

    // Getters & Setters
    public ScoreboardManager getScoreboardManager()
    {
        return scoreboardManager;
    }

    private void setScoreboardManager(ScoreboardManager scoreboardManager)
    {
        this.scoreboardManager = scoreboardManager;
    }

    public Scoreboard getScoreboard()
    {
        return scoreboard;
    }

    private void setScoreboard(Scoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
    }

}
