package SysSettings;
import Dashboard.*;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class settingsController implements Initializable {
    String URL;
    @FXML
    private JFXTextField textBoxURL;
    @FXML
    private Label setLab;
    String path = System.getProperty("user.home") + File.separator + "Documents/JBG_CRM/";
    File testDoc = new File(path+"settings.txt");


    //example on receiving data
    public void test(String T, String V){
        System.out.println(T);
        System.out.println(V);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(testDoc));
            URL = (reader.readLine());
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        textBoxURL.setText(URL);

    }


    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void saveSetChanges(ActionEvent actionEvent) throws IOException {
        FileWriter writer = new FileWriter(path + "settings.txt", false);
        String text = textBoxURL.getText();
        writer.write(text);
        writer.close();
        setLab.setText("Changes have been saved");


    }


}
