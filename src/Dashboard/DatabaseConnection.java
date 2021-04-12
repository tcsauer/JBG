package Dashboard;

import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DatabaseConnection {
    //URL in ./preferences/URL.txt file & updatable through GUI
    String URL;

    public void getURL() throws IOException {
        String tempURL;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/preferences/URL.txt")));
        tempURL = (txtReader.readLine());
        String path = System.getProperty("user.home") + File.separator + "Documents/JBG_CRM/";
        File customDir = new File(path);
        if(!customDir.exists()){
            System.out.println("Reading New File");
            customDir.mkdirs();
            customDir.createNewFile();
            FileWriter writer = new FileWriter(path+"settings.txt");
            writer.write(tempURL);
            writer.close();
            URL = tempURL;
        }
        else{
            System.out.println("Reading Old File");
            File testDoc = new File(path+"settings.txt");
            BufferedReader reader = new BufferedReader(new FileReader(testDoc));
            Scanner in = new Scanner(new FileReader(testDoc));

            try {
               URL = (reader.readLine());
            } finally {
                reader.close();
            }
        }

    }

    public Connection getConnectionPlain() throws IOException {
        getURL();
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "texstar", "all4gibbs");
        } catch (Exception e) {
            System.out.println("Error Occurred While Getting the Connection: - " + e);
        }
        return connection;
    }


    public Statement ConnectToDatabase() throws IOException {
        getURL();


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
