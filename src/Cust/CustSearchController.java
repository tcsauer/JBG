package Cust;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import Jobs.Jobs;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustSearchController {

    @FXML
    public JFXTextField textField;

    public String getTextFieldValue(){
        return textField.getText();
    }

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustJobsMain.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void custSearch(ActionEvent actionEvent) throws IOException {
        //findCust();
        //System.out.println(textField.getText());
        Parent SceneParent = FXMLLoader.load(getClass().getResource("SearchReturn.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
