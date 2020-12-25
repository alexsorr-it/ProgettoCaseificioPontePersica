package JAVA;

import java.sql.Connection;
import java.sql.DriverManager;

@SuppressWarnings({"ThrowablePrintedToSystemOut", "FieldMayBeFinal"})
public class MySqlDbConnection {
    private static MySqlDbConnection instance = null;
    private String dbDriver = "jdbc:mysql";
    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbUser;
    private String dbPassword;
    private String dbName;
    private String dbTimeZone = "Europe/Rome";

    private MySqlDbConnection() {
        dbUser = "root";
        dbPassword = "admin";
        dbName = "caseificio";
    }

    public static MySqlDbConnection getInstance() {
        if (instance == null)
            instance = new MySqlDbConnection();
        return instance;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbTimeZone() {
        return dbTimeZone;
    }

    public void setDbTimeZone(String dbTimeZone) {
        this.dbTimeZone = dbTimeZone;
    }

    public Connection connect() {
        String dbConnectionUrl = this.dbDriver + "://"
                + this.dbHost + ":"
                + this.dbPort + "/"
                + dbName
                + "?serverTimezone=" + dbTimeZone;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbConnectionUrl, dbUser, dbPassword);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

