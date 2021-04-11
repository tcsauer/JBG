package backup_scenes;

import Cust.CustEditController;
import Cust.CustJobsMainController;
import Cust.CustSearchController;
import Cust.SaveCust;
import Dashboard.DatabaseConnection;
import Jobs.AllJobCreateEditController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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


    ObservableList<SaveCust> custList = FXCollections.observableArrayList();
    @FXML
    private Label errorLab;
    @FXML
    private JFXButton resetBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT customer_id, cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer");
            while(rs.next()){
                custList.add(new SaveCust(rs.getInt("customer_id"),
                        rs.getString("cust_fname"),
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


        searchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        searchTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<SaveCust>() {
            @Override
            public void onChanged(Change<? extends SaveCust> c) {
                for(SaveCust s: c.getList()){
                    System.out.println(s.getCustID());
                }
            }
        });

    }

    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Cust/CustJobsMain.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void viewCstProfile(ActionEvent actionEvent) throws IOException {
        if(searchTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jobs/AllJobCreateEdit.fxml"));
            Parent root = loader.load();
            AllJobCreateEditController scene2Controller = loader.getController();
            scene2Controller.diffSceneCustID(searchTable.getSelectionModel().getSelectedItem().getCustID(), 1);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }else{
            errorLab.setVisible(true);
        }
    }

    @FXML
    private void custSearch(ActionEvent actionEvent) {
        resetBtn.setVisible(true);
        if(textField.getText() != null){
        try {
            searchTable.getItems().clear();
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT customer_id, cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer WHERE CONCAT (cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip) LIKE '%"+textField.getText()+"%'");
            while(rs.next()){
                custList.add(new SaveCust(rs.getInt("customer_id"),
                        rs.getString("cust_fname"),
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
        }}
        else {
            try {
                searchTable.getItems().clear();
                Statement con = ConnectToDatabase();
                ResultSet rs = con.executeQuery("SELECT customer_id, cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer");
                while(rs.next()){
                    custList.add(new SaveCust(rs.getInt("customer_id"),
                            rs.getString("cust_fname"),
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
            }
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

    @FXML
    private void deleteCustomer(ActionEvent actionEvent) throws IOException{
        if(searchTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Connection connection = getConnectionPlain();
                PreparedStatement ps = getConnectionPlain().prepareStatement("DELETE FROM Job WHERE customer_id = ?;");
                PreparedStatement p2 = connection.prepareStatement("DELETE FROM Customer WHERE customer_id = ?;");
                ps.setInt(1, searchTable.getSelectionModel().getSelectedItem().getCustID());
                p2.setInt(1, searchTable.getSelectionModel().getSelectedItem().getCustID());
                ps.executeUpdate();
                p2.executeUpdate();
                ps.close();
                p2.close();
                searchTable.getItems().removeAll(searchTable.getSelectionModel().getSelectedItem());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }else {
            errorLab.setVisible(true);
        }
        resetBtn.setVisible(false);
    }

    @FXML
    private void editCustomer(ActionEvent actionEvent) throws IOException{
        if(searchTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Cust/CustEdit.fxml"));
            Parent root = loader.load();
            CustEditController custInfoScene = loader.getController();
            custInfoScene.showInfo2(searchTable.getSelectionModel().getSelectedItem().getCustID(), searchTable.getSelectionModel().getSelectedItem().getCustFirstName(),
                    searchTable.getSelectionModel().getSelectedItem().getCustLastName(), searchTable.getSelectionModel().getSelectedItem().getCustPhone(),
                    searchTable.getSelectionModel().getSelectedItem().getCustEmail(), searchTable.getSelectionModel().getSelectedItem().getCustStreetAddress(),
                    searchTable.getSelectionModel().getSelectedItem().getCustState(), searchTable.getSelectionModel().getSelectedItem().getCustCity(),
                    searchTable.getSelectionModel().getSelectedItem().getCustZip());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }else{
            errorLab.setVisible(true);
        }
    }

    @FXML
    private void ajayButn(ActionEvent actionEvent) {
            try {
                searchTable.getItems().clear();
                Statement con = ConnectToDatabase();
                ResultSet rs = con.executeQuery("SELECT customer_id, cust_fname,cust_lname,cust_phone,cust_email,cust_street,cust_city,cust_state,cust_zip FROM Customer");
                while(rs.next()){
                    custList.add(new SaveCust(rs.getInt("customer_id"),
                            rs.getString("cust_fname"),
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

    @FXML
    private void clearError(MouseEvent mouseEvent) {
        errorLab.setVisible(false);
    }
}