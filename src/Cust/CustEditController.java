package Cust;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import backup_scenes.*;

public class CustEditController {
    @FXML
    private JFXTextField fName, lName, phone, email, streetAddress, city, zip, state;

    @FXML
    private TableView<String> custJobsTable;

    private int x;

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/backup_scenes/CustSearchAndReturnController.fxml"));
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
        Parent SceneParent = FXMLLoader.load(getClass().getResource("../Jobs/JobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    public void showInfo2(int custID, String firstName, String lastName, String custPhone, String custEmail, String custStreetAddress, String custCity, String custState, String custZip){
        this.x = custID;
        fName.setText(firstName);
        lName.setText(lastName);
        phone.setText(custPhone);
        email.setText(custEmail);
        streetAddress.setText(custStreetAddress);
        state.setText(custState);
        city.setText(custCity);
        zip.setText(custZip);
    }

}
