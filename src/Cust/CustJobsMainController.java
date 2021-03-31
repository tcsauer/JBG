package Cust;

import Dashboard.DatabaseConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;

public class CustJobsMainController extends DatabaseConnection {

    @FXML
    public JFXTextField firstNameBox, lastNameBox, phoneBox, emailBox, streetAddressBox, stateBox, cityBox, zipBox;

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToActiveJob(ActionEvent actionEvent) throws IOException {
        Parent newParent = FXMLLoader.load(getClass().getResource("../Jobs/ActiveJob.fxml"));
        Scene newScene = new Scene(newParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToCustSearch(ActionEvent actionEvent) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustSearch.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void saveForm(ActionEvent actionEvent) {
        try {
            Statement sqlInsert = ConnectToDatabase();
            sqlInsert.execute("INSERT INTO Customer(cust_fname, cust_lname, cust_phone, cust_email, cust_street, cust_city, cust_state, cust_zip) Values ('" + firstNameBox.getText() + "','" + lastNameBox.getText() + "','" + phoneBox.getText() + "','" + emailBox.getText() + "','" + streetAddressBox.getText() + "','" + cityBox.getText() + "','" + stateBox.getText() + "','" + zipBox.getText() + "')");
            disconnectFromDB(sqlInsert);
         }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        firstNameBox.clear();
        lastNameBox.clear();
        phoneBox.clear();
        emailBox.clear();
        streetAddressBox.clear();
        stateBox.clear();
        cityBox.clear();
        zipBox.clear();
    }

    @FXML
    private void saveAndChangeToJob(ActionEvent actionEvent) throws IOException {
        saveForm(actionEvent);
        Parent SceneParent = FXMLLoader.load(getClass().getResource("../Jobs/AllJobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }
}
