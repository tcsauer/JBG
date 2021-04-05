package backup_scenes;

import Cust.CustSearchController;
import Cust.SaveCust;
import Dashboard.DatabaseConnection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustSearchAndReturnController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<SaveCust> searchTable;
    @FXML
    private TableColumn<SaveCust,String> col_firstName;
    @FXML
    private TableColumn<SaveCust,String> col_lastName;
    @FXML
    private TableColumn<SaveCust,String> col_phone;
    @FXML
    private TableColumn<SaveCust,String> col_email;
    @FXML
    private TableColumn<SaveCust, String> col_street;
    @FXML
    private TableColumn<SaveCust, String> col_city;
    @FXML
    private TableColumn<SaveCust, String> col_state;
    @FXML
    private TableColumn<SaveCust, String> col_zip;
    @FXML
    private JFXTextField textField;

    private CustSearchController search;

    ObservableList<SaveCust> custList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            //LIKE '%'"+CustSearchController.textField.getText()+"'%'"
            ResultSet rs = con.executeQuery("SELECT cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer");
            //ResultSet rs = con.executeQuery("SELECT cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer WHERE CONCAT (cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip) LIKE '%"+CustSearchController.textField.getText()+"%'");
            while(rs.next()){
                custList.add(new SaveCust(rs.getString("cust_fname"),
                        rs.getString("cust_lname"),
                        rs.getString("cust_phone"),
                        rs.getString("cust_email"),
                        rs.getString("cust_street"),
                        rs.getString("cust_city"),
                        rs.getString("cust_state"),
                        rs.getString("cust_zip")));
            }disconnectFromDB(con);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(SearchReturnController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("custFirstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("custLastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("custPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("custStreetAddress"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("custState"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("custCity"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("custZip"));

        searchTable.setItems(custList);

    }

    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustSearchForJobs.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void viewCstProfile(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJobCreateEdit.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void custSearch(ActionEvent actionEvent) {
        try {
            searchTable.getItems().clear();
            Statement con = ConnectToDatabase();
            //LIKE '%'"+CustSearchController.textField.getText()+"'%'"
            ResultSet rs = con.executeQuery("SELECT cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer WHERE CONCAT (cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip) LIKE '%"+textField.getText()+"%'");
            //ResultSet rs = con.executeQuery("SELECT cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer WHERE CONCAT (cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip) LIKE '%"+CustSearchController.textField.getText()+"%'");
            while(rs.next()){
                custList.add(new SaveCust(rs.getString("cust_fname"),
                        rs.getString("cust_lname"),
                        rs.getString("cust_phone"),
                        rs.getString("cust_email"),
                        rs.getString("cust_street"),
                        rs.getString("cust_city"),
                        rs.getString("cust_state"),
                        rs.getString("cust_zip")));
            }disconnectFromDB(con);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(SearchReturnController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("custFirstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("custLastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("custPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("custStreetAddress"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("custState"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("custCity"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("custZip"));

        searchTable.setItems(custList);
        textField.setText(null);
    }
}