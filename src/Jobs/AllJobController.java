package Jobs;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import QuickInvoice.quickInvoiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllJobController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<Jobs> AllJobsTable;
    @FXML
    private TableColumn<Jobs,String> col_sketch;
    @FXML
    private TableColumn<Jobs,String> col_type;
    @FXML
    private TableColumn<Jobs,String> col_cost;
    @FXML
    private TableColumn<Jobs,String> col_status;
    @FXML
    private TableColumn<Jobs, String> col_dateStart;
    @FXML
    private TableColumn<Jobs, String> col_dateComplete;
    @FXML
    private TableColumn<Jobs, String> col_payment;

    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_sketch, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job");
            while(rs.next()){
                jobList.add(new Jobs(rs.getString("job_sketch"),rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start"),rs.getString("date_complete"),rs.getString("payment_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(quickInvoiceController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_sketch.setCellValueFactory(new PropertyValueFactory<>("jobSketch"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        col_dateComplete.setCellValueFactory(new PropertyValueFactory<>("dateComplete"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        AllJobsTable.setItems(jobList);
    }

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("../dashboard/dashboard.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToViewJobs(ActionEvent actionEvent) throws IOException {
//Needs to be done



        Parent SceneParent = FXMLLoader.load(getClass().getResource("AllJobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void printJob(ActionEvent actionEvent) {
    }

    @FXML
    private void completeJob(ActionEvent actionEvent) {
    }

    @FXML
    private void newJob(ActionEvent actionEvent) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustSearchForJobs.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
