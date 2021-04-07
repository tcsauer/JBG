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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToActiveJob(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jobs/ActiveJob.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    private void changeToCustSearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backup_scenes/custSearchAndReturn.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
        //saveForm(actionEvent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jobs/AllJobCreateEdit.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }
}
