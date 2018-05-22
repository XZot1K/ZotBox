package XZot1K.plugins.zb.utils;

import XZot1K.plugins.zb.ZotBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker
{

    private ZotBox pluginInstance;
    private int projectId;
    private URL checkURL;
    private String newVersion;

    public UpdateChecker(int projectId)
    {
        this.pluginInstance = ZotBox.getInstance();
        this.newVersion = pluginInstance.getDescription().getVersion();
        this.projectId = projectId;
        try
        {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectId);
        } catch (MalformedURLException ignored) {}
    }

    public int getProjectId()
    {
        return projectId;
    }

    public String getLatestVersion()
    {
        return newVersion;
    }

    public String getResourceURL()
    {
        return "https://www.spigotmc.org/resources/" + projectId;
    }

    public boolean checkForUpdates()
    {
        try
        {
            URLConnection con = checkURL.openConnection();
            this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            return !pluginInstance.getDescription().getVersion().equals(newVersion);
        } catch (Exception ignored) {}
        return true;
    }

}
