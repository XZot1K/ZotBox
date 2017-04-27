package XZot1K.plugins.zb.utils.levelsystem;

public class LSData
{

    private LevelSystem levelSystem;
    private int level, currentExperience;

    public LSData(LevelSystem levelSystem, int level, int experience)
    {
        setLevelSystem(levelSystem);
        setLevel(level);
        setCurrentExperience(experience);
    }

    public boolean isMaxLevel()
    {
        return getLevelSystem().getMaxLevel() > -1 && getLevel() >= getLevelSystem().getMaxLevel();
    }

    public LSData addExperience(int amount)
    {
        if (!isMaxLevel())
        {
            int neededExperience = getLevelSystem().getNeededExperience(this),
                    total = (getCurrentExperience() + amount), levelsToAdd = (total / neededExperience);
            if (getLevelSystem().doesExperienceResetUponLevel()) { setCurrentExperience(total % neededExperience); }
            if (!(getLevelSystem().getMaxLevel() <= -1) && levelsToAdd > getLevelSystem().getMaxLevel())
            { setLevel(levelSystem.getMaxLevel()); } else { setLevel(getLevel() + levelsToAdd); }
        }

        return this;
    }

    public LSData removeExperience(int amount)
    {
        if (getLevel() <= 1 && getCurrentExperience() < amount) { return this; }
        if (getCurrentExperience() >= amount) { setCurrentExperience(getCurrentExperience() - amount); } else
        {
            int remainingAmount = (amount - getCurrentExperience());
            if (remainingAmount > 0)
            {
                setLevel(getLevel() - 1);
                setCurrentExperience(getLevelSystem().getNeededExperience(getLevel(), 0));
                removeExperience(remainingAmount);
            }
        }

        return this;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getCurrentExperience()
    {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience)
    {
        this.currentExperience = currentExperience;
    }

    public LevelSystem getLevelSystem()
    {
        return levelSystem;
    }

    public void setLevelSystem(LevelSystem levelSystem)
    {
        this.levelSystem = levelSystem;
    }
}
