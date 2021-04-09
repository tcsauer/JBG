package Dashboard;

import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseConnection {
    //URL in ./preferences/URL.txt file & updatable through GUI
    String URL;

    public void getURL(){
        try {
            String tempURL;
            FileReader reader = new FileReader("./preferences/URL.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            tempURL = bufferedReader.readLine();
            reader.close();
            if(tempURL.isEmpty()){
                URL = "jdbc:mysql://jbgdev.cmsdvfssc2oc.us-east-2.rds.amazonaws.com:3306/JBandG";
            }
            else URL = tempURL;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnectionPlain() {
        getURL();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "texstar", "all4gibbs");
        } catch (Exception e) {
            System.out.println("Error Occured While Getting the Connection: - " + e);
        }
        return connection;
    }


    public Statement ConnectToDatabase() {
        try {
            FileReader reader = new FileReader("./preferences/URL.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            URL = bufferedReader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, "texstar", "all4gibbs");
            Statement stmt = connection.createStatement();
            System.out.println("Remote connection successful.");
            return stmt;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
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
