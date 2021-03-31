package Cust;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustEditController {
    @FXML
    private JFXTextField fName;

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustJobsMain.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void editAndSave(ActionEvent actionEvent) {
        //Needs to be done
        //This is to test error label alt
        fName.getStylesheets().add("assets/error.css");
        fName.setPromptText("Please enter letters only");
    }

    @FXML
    private void viewJob(ActionEvent actionEvent) throws IOException {
//Needs to be done




        Parent SceneParent = FXMLLoader.load(getClass().getResource("../Jobs/JobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void jobComplete(ActionEvent actionEvent) {
//Needs to be done
    }

    @FXML
    private void deleteJob(ActionEvent actionEvent) {
    }

    @FXML
    private void DeleteCustomer(ActionEvent actionEvent) {
    }
}
