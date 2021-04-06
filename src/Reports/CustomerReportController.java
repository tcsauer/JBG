package Reports;

import Dashboard.DataStore2;
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

public class CustomerReportController extends DatabaseConnection implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private TableView<DataStore2> activeTable2;
    @FXML
    private TableColumn<DataStore2, String> col_fname;
    @FXML
    private TableColumn<DataStore2, String> col_lname;
    @FXML
    private TableColumn<DataStore2, String> col_phone;
    @FXML
    private TableColumn<DataStore2, String> col_email;
    @FXML
    private TableColumn<DataStore2, String> col_street;
    @FXML
    private TableColumn<DataStore2, String> col_city;
    @FXML
    private TableColumn<DataStore2, String> col_state;
    @FXML
    private TableColumn<DataStore2, String> col_zip;

    ObservableList<DataStore2> activeList2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT cust_fname, cust_lname, cust_phone, cust_email, cust_street, cust_city, cust_state, cust_zip FROM Customer");
            while (rs.next()) {
                activeList2.add(new DataStore2(rs.getString("cust_fname"), rs.getString("cust_lname"), rs.getString("cust_phone"), rs.getString("cust_email"), rs.getString("cust_street"), rs.getString("cust_city"), rs.getString("cust_state"), rs.getString("cust_zip")));
            }
            disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("street"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("zip"));

        activeTable2.setItems(activeList2);
        //Filter
        FilteredList<DataStore2> filteredData = new FilteredList<>(activeList2, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(DataStore2 -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (DataStore2.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (DataStore2.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<DataStore2> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(activeTable2.comparatorProperty());
        activeTable2.setItems(sortedData);
//end of filter
    }

        @FXML
        public void searchReport (ActionEvent actionEvent) throws IOException {
            System.out.println("DONE");
        }
        @FXML
        private void changeToDash (ActionEvent event) throws IOException {
            Parent SceneParent = FXMLLoader.load(getClass().getResource("/Dashboard/dashboard.fxml"));
            Scene newScene = new Scene(SceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        }


        @FXML
        private void printReport (ActionEvent actionEvent){
//Needs to be Started
        }

        @FXML
        private void ExportReport (ActionEvent actionEvent){
//Needs to be started
        }


    }