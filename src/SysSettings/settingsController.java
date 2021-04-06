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
import java.util.prefs.Preferences;

public class settingsController implements Initializable {
    String URL;
    @FXML
    private JFXTextField textBoxURL;
    @FXML
    private Label setLab;


    //example on receiving data
    public void test(String T, String V){
        System.out.println(T);
        System.out.println(V);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileReader reader = new FileReader("./preferences/URL.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            URL = bufferedReader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        FileWriter writer = new FileWriter("./preferences/URL.txt", false);
        String text = textBoxURL.getText();
        writer.write(text);
        writer.close();
        setLab.setText("Changes have been saved");


    }


}
