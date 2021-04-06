package Reports;
import Dashboard.DataStore2;
import Dashboard.DataStore3;
import Dashboard.DataStore5;
import Dashboard.DatabaseConnection;
import Reports.CustomerReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pastJobsController extends DatabaseConnection implements Initializable{
    @FXML
    private TextField search;
    @FXML
    private TableView<DataStore5> activeTable5;
    @FXML
    private TableColumn<DataStore5, String> col_fname;
    @FXML
    private TableColumn<DataStore5, String> col_lname;
    @FXML
    private TableColumn<DataStore5, String> col_id;
    @FXML
    private TableColumn<DataStore5, String> col_type;
    @FXML
    private TableColumn<DataStore5, String> col_cost;
    @FXML
    private TableColumn<DataStore5, String> col_status;
    @FXML
    private TableColumn<DataStore5, String> col_date;

    ObservableList<DataStore5> activeList5 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT Customer.cust_fname, Customer.cust_lname, Job.job_id, Job.job_type, Job.job_cost, Job.job_status, Job.date_complete FROM Customer JOIN Job ON Job.customer_id = Customer.customer_id;");
            while (rs.next()) {
                activeList5.add(new DataStore5(rs.getString("Customer.cust_fname"), rs.getString("Customer.cust_lname"), rs.getString("Job.job_id"), rs.getString("Job.job_type"), rs.getString("Job.job_cost"), rs.getString("Job.job_status"), rs.getString("Job.date_complete")));
            }
            disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(pastJobsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        activeTable5.setItems(activeList5);

        //Filter
        FilteredList<DataStore5> filteredData = new FilteredList<>(activeList5, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(DataStore5 -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (DataStore5.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (DataStore5.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<DataStore5> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(activeTable5.comparatorProperty());
        activeTable5.setItems(sortedData);
//end of filter

    }


//Buttons
    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void printReport(ActionEvent actionEvent) {
//Needs to be Started
    }

    @FXML
    private void ExportReport(ActionEvent actionEvent) {
//Needs to be started
    }
}
