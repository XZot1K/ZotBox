package XZot1K.plugins.zb.utils;

public class SpeedTest
{
    private long startTimeStamp, stopTimeStamp;

    public SpeedTest()
    {
        setStartTimeStamp(0);
        setStopTimeStamp(0);
    }

    /**
     * Sets the first time stamp to set how long whatever your using this for took to run.
     */
    public void start()
    {
        setStartTimeStamp(System.currentTimeMillis());
    }

    /**
     * Sets the second time stamp to set how long whatever your using this for took to run.
     */
    public void stop()
    {
        setStopTimeStamp(System.currentTimeMillis());
    }

    /**
     * Retrieves the total run time of whatever your using this for.
     */
    public long getRunTime()
    {
        return getStopTimeStamp() - getStartTimeStamp();
    }

    public long getStartTimeStamp()
    {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp)
    {
        this.startTimeStamp = startTimeStamp;
    }

    public long getStopTimeStamp()
    {
        return stopTimeStamp;
    }

    public void setStopTimeStamp(long stopTimeStamp)
    {
        this.stopTimeStamp = stopTimeStamp;
    }

}
