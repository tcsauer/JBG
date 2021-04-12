package Cust;

import Dashboard.DatabaseConnection;
import Dashboard.Validation;
import Jobs.Jobs;
import backup_scenes.CustSearchAndReturnController;
import Jobs.AllJobCreateEditController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustEditController extends DatabaseConnection implements Initializable {
    @FXML
    private JFXTextField fName, lName, phone, email, streetAddress, city, zip, state;

    @FXML
    private TableView<Jobs> custJobsTable;

    @FXML
    private TableColumn<Jobs, String> col_type, col_cost, col_status, col_dateStart, col_dateComplete, col_payment;

    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    private int x;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void loadJob(ActionEvent actionEvent){
        try{
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_id, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job WHERE customer_id = '"+x+"'");
            while(rs.next()){
                jobList.add(new Jobs(rs.getInt("job_id"),
                        rs.getString("job_type"),
                        rs.getString("job_cost"),
                        rs.getString("job_status"),
                        rs.getString("date_start"),
                        rs.getString("date_complete"),
                        rs.getString("payment_type")));
            }disconnectFromDB(con);
        }catch(SQLException ex){
            Logger.getLogger(CustEditController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        col_dateComplete.setCellValueFactory(new PropertyValueFactory<>("dateComplete"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        custJobsTable.setItems(jobList);
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
    private void editAndSave(ActionEvent actionEvent) {
        if(Validation.textFieldNotEmpty(fName, lName, phone, email, streetAddress, state, city, zip))
        if(Validation.phoneFormat(phone))
        if(Validation.emailFormat(email))
        if(Validation.zipFormat(zip)){
            try {
                Statement sqlUpdate = ConnectToDatabase();
                //sqlUpdate.execute("UPDATE Customer SET cust_fname = '" + fName.getText() + "', cust_lname = '" + lName.getText() + "', cust_phone = '" + phone.getText() + "', cust_email = '" + email.getText() + "', cust_street = '" + streetAddress.getText() + "', cust_city = '" + city.getText() + "', cust_state = '" + state.getText() + "', cust_zip = '" + zip.getText() + "' WHERE customer_id = '" + x + "'");
                disconnectFromDB(sqlUpdate);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            fName.clear();
            lName.clear();
            phone.clear();
            email.clear();
            streetAddress.clear();
            state.clear();
            city.clear();
            zip.clear();
        }
    }

    @FXML
    private void viewJob(ActionEvent actionEvent) throws IOException {
        if(custJobsTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jobs/AllJobCreateEdit.fxml"));
            Parent root = loader.load();
            AllJobCreateEditController editScene2 = loader.getController();
            editScene2.diffSceneCustID(custJobsTable.getSelectionModel().getSelectedItem().getJobID(), 2);
            editScene2.showInfo(custJobsTable.getSelectionModel().getSelectedItem().getJobType(), custJobsTable.getSelectionModel().getSelectedItem().getJobCost(), custJobsTable.getSelectionModel().getSelectedItem().getJobStatus(), custJobsTable.getSelectionModel().getSelectedItem().getPaymentType(), custJobsTable.getSelectionModel().getSelectedItem().getDateStart(), custJobsTable.getSelectionModel().getSelectedItem().getDateComplete());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }else{
            JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Please select a job.");
        }
    }

}
