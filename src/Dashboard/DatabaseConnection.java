package Dashboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
//Help Here! Keep Getting Connection Errors...
    final static String hostname = "jbgdev.cmsdvfssc2oc.us-east-2.rds.amazonaws.com";
    final static String dbName = "test";
    final static String port = "3306";
    final static String username = "texstar";
    final static String password = "all4gibbs";
    final static String DB_URL_PREFACE = "jdbc:mysql://";
    public static String connectionUrl =
            "jdbc:mysql://jbgdev.cmsdvfssc2oc.us-east-2.rds.amazonaws.com:3306/test?user=texstar&password=all4gibbs";

    public static Statement ConnectToDatabase()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL_PREFACE + hostname + ":" + port + "/" + dbName, username, password );
            Statement stmt = connection.createStatement();
            System.out.println("Remote connection successful.");
            return stmt;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Connecting");
        }
        return null;
    }

    public void disconnectFromDB(Statement stmt){
        try {
            stmt.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Successfully disconnected from DB");
    }
}
