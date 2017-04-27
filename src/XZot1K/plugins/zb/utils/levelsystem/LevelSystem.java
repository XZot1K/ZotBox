package XZot1K.plugins.zb.utils.levelsystem;

import XZot1K.plugins.zb.ZotBox;

public class LevelSystem
{

    private ZotBox plugin;
    private String experienceFormula;
    private int maxLevel;
    private boolean resetExperienceUponLevel;

    /**
     * Creates a entirely new Level System for you to enjoy! The point of this is to save time when it comes to leveling systems.
     * You can use this for almost anything! Just feed it your formula and store the LSData (Level System Data) in a hashmap
     * with a entity or even a UUID to keep track of it! You use this LSData to get the level, add experience, remove experience,
     * and even get the experience.
     *
     * @param experienceFormula This is the formula used to calculate the experience needed for each level to level up.
     *                          You can use the following placeholders anywhere in the formula: {level} and {current-experience}.
     */
    public LevelSystem(String experienceFormula)
    {
        plugin = ZotBox.getInstance();
        setExperienceFormula(experienceFormula);
        setMaxLevel(-1);
        setResetExperienceUponLevel(true);
    }

    /**
     * This gets the needed experience that is needed to level up from the given Level System Data.
     *
     * @param lsData The Level System Data that you want to get the needed experience for.
     *
     * @return The needed experience to level up.
     */
    public int getNeededExperience(LSData lsData)
    {
        int experienceNeeded = 0;
        try
        {
            experienceNeeded = (int) plugin.getCalculationLibrary().calculateFormula(getExperienceFormula()
                    .replace("{level}", String.valueOf(lsData.getLevel()))
                    .replace("{current-experience}", String.valueOf(lsData.getCurrentExperience())));
        } catch (Exception e) { e.printStackTrace(); }
        return experienceNeeded;
    }

    /**
     * This gets the needed experience that is needed to level up from the given level and current experience.
     *
     * @param level             The level.
     * @param currentExperience The current experience.
     *
     * @return The needed experience to level up.
     */
    public int getNeededExperience(int level, int currentExperience)
    {
        int experienceNeeded = 0;
        try
        {
            experienceNeeded = (int) plugin.getCalculationLibrary().calculateFormula(getExperienceFormula()
                    .replace("{level}", String.valueOf(level))
                    .replace("{current-experience}", String.valueOf(currentExperience)));
        } catch (Exception e) { e.printStackTrace(); }
        return experienceNeeded;
    }

    // Getters & Setters

    /**
     * Gets the experience formula of this level system.
     *
     * @return The formula.
     */
    public String getExperienceFormula()
    {
        return experienceFormula;
    }

    /**
     * Sets the experience formula for this level system.
     *
     * @param experienceFormula The formula you want to set the level system's formula to.
     */
    public void setExperienceFormula(String experienceFormula)
    {
        this.experienceFormula = experienceFormula;
    }

    /**
     * Gets the max level of this level system.
     *
     * @return The max level.
     */
    public int getMaxLevel()
    {
        return maxLevel;
    }

    /**
     * Sets the max level of this level system. By default this is set to -1 which means unlimited.
     *
     * @param maxLevel The level you want to set the max level to.
     */
    public void setMaxLevel(int maxLevel)
    {
        this.maxLevel = maxLevel;
    }

    /**
     * Gets if experience resets upon leveling up.
     *
     * @return Whether experience resets upon leveling up or not.
     */
    public boolean doesExperienceResetUponLevel()
    {
        return resetExperienceUponLevel;
    }

    /**
     * Sets if the experience upon leveling up resets or not. By default this is "true".
     *
     * @param resetExperienceUponLevel Yes or No.
     */
    public void setResetExperienceUponLevel(boolean resetExperienceUponLevel)
    {
        this.resetExperienceUponLevel = resetExperienceUponLevel;
    }
}
