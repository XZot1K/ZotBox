package XZot1K.plugins.zb.libraries;

import XZot1K.plugins.zb.ZotBox;

import java.sql.*;

public class DatabaseLibrary
{

    private ZotBox plugin = ZotBox.getInstance();

    /**
     * Establishes a new MySQL connection without a database. (Only use this for creating a database then use the other method)
     *
     * @param databaseHost     The database IP address.
     * @param databasePort     The database IP address port.
     * @param databaseUsername The database username.
     * @param databasePassword The database password.
     * @return A brand new MySQL connection.
     */
    public Connection createMySQLConnection(String databaseHost, String databasePort, String databaseUsername, String databasePassword)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String cd = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/?user=" + databaseUsername + "&password=" + databasePassword;
            connection = DriverManager.getConnection(cd);
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully established a new connection with the following information: "
                    + " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort);
        } catch (Exception e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't establish a new connection with the following information: "
                    + " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort);
        }
        return connection;
    }

    /**
     * Establishes a new MySQL connection with a database.
     *
     * @param databaseHost     The database IP address.
     * @param databasePort     The database IP address port.
     * @param databaseUsername The database username.
     * @param databasePassword The database password.
     * @param databaseName     The database name.
     * @return A brand new MySQL connection.
     */
    public Connection createMySQLConnection(String databaseHost, String databasePort, String databaseUsername, String databasePassword, String databaseName)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?autoReconnect=true";
            connection = DriverManager.getConnection(url, databaseUsername, databasePassword);
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully established a new connection with the following information: "
                    + " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eDatabase: &6" + databaseName);
        } catch (Exception e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't establish a new connection with the following information: "
                    + " &eHost: &6" + databaseHost + " &ePort: &6" + databasePort + " &eDatabase: &6" + databaseName);
        }
        return connection;
    }

    /**
     * Creates a new database within a specified MySQL connection.
     * (Use the "createMySQLConnection" method without a database for the connection to do this)
     *
     * @param connection   The MySQL connection.
     * @param databaseName The database name.
     */
    public void createMySQLDatabase(Connection connection, String databaseName)
    {
        try
        {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName + ";");
            s.close();
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessful created a new database with the name &e" + databaseName + "&a.");
        } catch (SQLException e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't create a new database with the name &e" + databaseName + "&c.");
        }
    }

    /**
     * Creates a new table within a specified MySQL connection. (Connection must contain database)
     *
     * @param connection The MySQL connection.
     * @param tableName  The MySQL table name.
     * @param columns    The MySQL columns.
     */
    public void createMySQLTable(Connection connection, String tableName, String columns)
    {
        try
        {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")");
            s.close();
            plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessful created a new table with the name &e" + tableName + "&a.");
        } catch (SQLException e)
        {
            e.printStackTrace();
            plugin.getGeneralLibrary().sendConsoleMessage("&cCouldn't create a new table with the name &e" + tableName + "&c.");
        }
    }

    /**
     * Creates a new MySQL result set with the specified MySQL connection.
     *
     * @param connection The MySQL connection.
     * @param syntax     The MySQL statement syntax.
     * @return The result set.
     */
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

    /**
     * Creates a new MySQL prepared statement with the specified MySQL connection.
     * @param connection The MySQL connection.
     * @param syntax The MySQL statement syntax.
     * @return The prepared statement.
     */
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

    /**
     * Creates a new MySQL statement with the specified MySQL connection.
     *
     * @param connection The MySQL connection.
     * @return The statement.
     */
    public Statement createMySQLStatement(Connection connection)
    {
        try
        {
            return connection.createStatement();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
