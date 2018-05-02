package XZot1K.plugins.zb.utils;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class CustomScoreboard
{

    private ZotBox plugin = ZotBox.getInstance();
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

    @SuppressWarnings("deprecation")
	public CustomScoreboard registerTeam(String teamName, String prefix, String suffix, boolean allowFriendlyFire,
                                         boolean canSeeFriendlyInvisibles, NameTagVisibility nameTagVisibility)
    {
        Team team = getScoreboard().getTeam(teamName);
        if (team == null)
        {
            team = getScoreboard().registerNewTeam(teamName);
        }

        team.setPrefix(plugin.getGeneralLibrary().color(prefix));
        team.setSuffix(plugin.getGeneralLibrary().color(suffix));
        team.setAllowFriendlyFire(allowFriendlyFire);
        team.setCanSeeFriendlyInvisibles(canSeeFriendlyInvisibles);
        team.setNameTagVisibility(nameTagVisibility);
        return this;
    }

    @SuppressWarnings("deprecation")
	public CustomScoreboard addPlayerToTeam(String teamName, Player player)
    {
        Team team = getScoreboard().getTeam(teamName);
        if (team != null && !team.getPlayers().contains(player))
        {
            team.addPlayer(player);
        }

        return this;
    }

    @SuppressWarnings("deprecation")
	public CustomScoreboard removePlayerToTeam(String teamName, Player player)
    {
        Team team = getScoreboard().getTeam(teamName);
        if (team != null && team.getPlayers().contains(player))
        {
            team.removePlayer(player);
        }

        return this;
    }

    public CustomScoreboard showAllPlayers()
    {
        List<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());
        for (int i = -1; ++i < players.size(); )
        {
            showPlayer(players.get(i));
        }

        return this;
    }

    public CustomScoreboard hideAllPlayers()
    {
        List<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());
        for (int i = -1; ++i < players.size(); )
        {
            hidePlayer(players.get(i));
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

    public Team getTeam(String teamName)
    {
        return getScoreboard().getTeam(teamName);
    }

    public boolean doesTeamExist(String teamName) { return getTeam(teamName) != null; }

    public Objective getObjective(String objectiveName) { return getScoreboard().getObjective(objectiveName); }

    public boolean doesObjectiveExist(String objectiveName) { return getObjective(objectiveName) != null; }

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
