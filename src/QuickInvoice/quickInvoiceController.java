package QuickInvoice;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
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

// for excel

//end excel
public class quickInvoiceController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<DataStore> activeTable;
    @FXML
    private TableColumn<DataStore,String> col_fname;
    @FXML
    private TableColumn<DataStore,String> col_lname;
    @FXML
    private TableColumn<DataStore,String> col_phone;
    @FXML
    private TableColumn<DataStore,String> col_email;
    @FXML
    private TableColumn<DataStore, String> col_street;
    @FXML
    private TableColumn<DataStore, String> col_city;
    @FXML
    private TableColumn<DataStore, String> col_state;
    @FXML
    private TableColumn<DataStore, String> col_zip;
    @FXML
    private TableColumn<DataStore, String> col_cost;
    @FXML
    private TableColumn<DataStore, String> col_type;

    ObservableList<DataStore> activeList = FXCollections.observableArrayList();

 @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT Customer.cust_fname, Customer.cust_lname, Customer.cust_phone, Customer.cust_email, Customer.cust_street, Customer.cust_city, Customer.cust_state, Customer.cust_zip, Job.job_cost, Job.job_type FROM Customer JOIN Job ON Job.customer_id = Customer.customer_id WHERE Job.job_status = 'Pending';");
            while(rs.next()){
                activeList.add(new DataStore(rs.getString("Customer.cust_fname"),rs.getString("Customer.cust_lname"),rs.getString("Customer.cust_phone"),rs.getString("Customer.cust_email"),rs.getString("Customer.cust_street"),rs.getString("Customer.cust_city"),rs.getString("Customer.cust_state"),rs.getString("Customer.cust_zip"),rs.getString("Job.job_cost"),rs.getString("Job.job_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(quickInvoiceController.class.getName()).log(Level.SEVERE,null,ex);
        }

        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("street"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));

        activeTable.setItems(activeList);

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
    private void printInvoice(ActionEvent actionEvent) {
//Needs to be started
    }

    @FXML
    private void ExportInvoice(ActionEvent actionEvent) {
//Needs to be started


    }//end export
}
