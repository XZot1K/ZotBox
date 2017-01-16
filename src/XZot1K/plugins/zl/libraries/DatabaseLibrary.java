package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;

import java.sql.*;

public class DatabaseLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    public Connection createMySQLConnection(String databaseHost, String databasePort, String databaseUsername, String databasePassword)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String cd = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/?user=" + databaseUsername + "&password=" + databasePassword;
            connection = DriverManager.getConnection(cd);
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully established a new connection with the following information: " +
                    " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eUsername: &6" + databaseUsername + " &6Password: &6"
                    + databasePassword);
        } catch (Exception e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't establish a new connection with the following information: " +
                    " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eUsername: &6" + databaseUsername + " &ePassword: &6"
                    + databasePassword);
        }
        return connection;
    }

    public Connection createMySQLConnection(String databaseHost, String databasePort, String databaseUsername, String databasePassword, String databaseName)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName;
            DriverManager.getConnection(url, databaseUsername, databasePassword);
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully established a new connection with the following information: " +
                    " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eUsername: &6" + databaseUsername + " &6Password: &6"
                    + databasePassword + " &eDatabase: &6" + databaseName);
        } catch (Exception e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't establish a new connection with the following information: " +
                    " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eUsername: &6" + databaseUsername + " &ePassword: &6"
                    + databasePassword + " &eDatabase: &6" + databaseName);
        }
        return connection;
    }

    public void createMySQLDatabase(Connection connection, String databaseName)
    {
        try
        {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName + ";");
            s.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createMySQLTable(Connection connection, String databaseName, String tableName)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (uuid varchar(255), level int, max_level int, current_experience int, player_kills int, entity_kills int, deaths int, killstreak int,"
                    + " bonus_damage double, bonus_health double, max_bonus_damage double, max_bonus_health double)");
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet createMySQLResultSet(Connection connection, String syntax)
    {
        try
        {
            return connection.createStatement().executeQuery(syntax);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public PreparedStatement createMySQLPreparedStatement(Connection connection, String syntax)
    {
        try
        {
            return connection.prepareStatement(syntax);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
