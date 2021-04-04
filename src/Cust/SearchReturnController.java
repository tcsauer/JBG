package Cust;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import Jobs.Jobs;
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

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchReturnController extends DatabaseConnection implements Initializable{

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

    private CustSearchController search;

    ObservableList<SaveCust> custList = FXCollections.observableArrayList();

    private void getValue(){
        String value = search.getTextFieldValue();
        System.out.println(value);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                Statement con = ConnectToDatabase();
                getValue();
                //LIKE '%'"+CustSearchController.textField.getText()+"'%'"
                ResultSet rs = con.executeQuery("SELECT cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer WHERE cust_fname LIKE '%"+"Lari"+"%'");
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


    /*
    public ObservableList<SaveCust> ListCust (String ValToSearch) {
        ResultSet rs;

        try {
            Statement con = ConnectToDatabase();
            String searchQuery = "SELECT `cust_fname`,`cust_lname`,`cust_phone`,`cust_email`,`cust_street`,`cust_city`,`cust_state`,`cust_zip` FROM `Customer` WHERE CONCAT (`cust_fname`,`cust_lname`,`cust_phone`,`cust_email`,`cust_street`,`cust_city`,`cust_state`,`cust_zip`) LIKE '%"+CustSearchController.textField.getText()+"%'";
            rs = con.executeQuery(searchQuery);

            SaveCust cust;

            while(rs.next()){
                cust = new SaveCust(
                        rs.getString("cust_fname"),
                        rs.getString("cust_lname"),
                        rs.getString("cust_phone"),
                        rs.getString("cust_email"),
                        rs.getString("cust_street"),
                        rs.getString("cust_city"),
                        rs.getString("cust_state"),
                        rs.getString("cust_zip")
                );
                custList.add(cust);
            }
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return custList;
    }

    public void findCust() {
        ObservableList<SaveCust> customers = ListCust(CustSearchController.textField.getText());
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("custFirstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("custLastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("custPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("custStreetAddress"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("custState"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("custCity"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("custZip"));

        searchTable.setItems(customers);
    }
    */

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustSearch.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void viewCstProfile(ActionEvent actionEvent) throws IOException {





        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

}
