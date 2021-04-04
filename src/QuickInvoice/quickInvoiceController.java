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

public class quickInvoiceController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<DataStore> activeTable;
    @FXML
    private TableColumn<DataStore,String> col_sketch;
    @FXML
    private TableColumn<DataStore,String> col_type;
    @FXML
    private TableColumn<DataStore,String> col_cost;
    @FXML
    private TableColumn<DataStore,String> col_status;
    @FXML
    private TableColumn<DataStore, String> col_dateStart;

    ObservableList<DataStore> activeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_sketch, job_type, job_cost, job_status, date_start FROM Job");
            while(rs.next()){
                activeList.add(new DataStore(rs.getString("job_sketch"),rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(quickInvoiceController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_sketch.setCellValueFactory(new PropertyValueFactory<>("jobSketch"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));

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
    }
}
