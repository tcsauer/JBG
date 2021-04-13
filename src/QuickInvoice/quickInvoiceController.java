package QuickInvoice;
import java.time.LocalDate;
import java.time.Month;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class quickInvoiceController extends DatabaseConnection implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fileName;
    @FXML
    private Button setButton;
    @FXML
    private Button exportButton;
    @FXML
    private TableView<DataStore> activeTable;
    @FXML
    private TableColumn<DataStore, String> col_fname;
    @FXML
    private TableColumn<DataStore, String> col_lname;
    @FXML
    private TableColumn<DataStore, String> col_phone;
    @FXML
    private TableColumn<DataStore, String> col_email;
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

    @FXML
    private TextField dateF;
    @FXML
    private TextField firstNameF;
    @FXML
    private TextField lastNameF;
    @FXML
    private TextField phoneF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField streetF;
    @FXML
    private TextField cityF;
    @FXML
    private TextField stateF;
    @FXML
    private TextField zipF;
    @FXML
    private TextField costF;
    @FXML
    private TextField typeF;
    @FXML
    private TextField dueDate;
    @FXML
    private TextField salesPerson;

    @FXML private TextField f1;
    @FXML private TextField f2;
    @FXML private TextField f3;
    @FXML private TextField f4;
    @FXML private TextField f5;
    @FXML private TextField f6;
    @FXML private TextField f7;
    @FXML private TextField f8;
    @FXML private TextField f9;
    @FXML private TextField f10;
    @FXML private TextField f11;
    @FXML private TextField f12;
    @FXML private TextField f13;
    @FXML private TextField f14;
    @FXML private TextField f15;
    @FXML private TextField f16;
    @FXML private TextField f17;
    @FXML private TextField f18;
    @FXML private TextField f19;
    @FXML private TextField f20;
    @FXML private TextField f21;
    @FXML private TextField f22;
    @FXML private TextField f23;
    @FXML private TextField f24;
    @FXML private TextField f25;
    @FXML private TextField f26;
    @FXML private TextField f27;
    @FXML private TextField f28;
    @FXML private TextField f29;
    @FXML private TextField f30;

//for click selection


    //end click
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exportButton.setDisable(true);
        fileName.setDisable(true);
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT Customer.cust_fname, Customer.cust_lname, Customer.cust_phone, Customer.cust_email, Customer.cust_street, Customer.cust_city, Customer.cust_state, Customer.cust_zip, Job.job_cost, Job.job_type FROM Customer JOIN Job ON Job.customer_id = Customer.customer_id WHERE Job.job_status = 'Pending';");
            while (rs.next()) {
                activeList.add(new DataStore(rs.getString("Customer.cust_fname"), rs.getString("Customer.cust_lname"), rs.getString("Customer.cust_phone"), rs.getString("Customer.cust_email"), rs.getString("Customer.cust_street"), rs.getString("Customer.cust_city"), rs.getString("Customer.cust_state"), rs.getString("Customer.cust_zip"), rs.getString("Job.job_cost"), rs.getString("Job.job_type")));
            }
            disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(quickInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
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

        //retrieve selected rows


        //end retieve

    }



    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //export
    @FXML
    private void exportInvoice(ActionEvent actionEvent) throws IOException {

//start pic
           SnapshotParameters sp = new SnapshotParameters();
           sp.setTransform(Transform.scale(5, 5));
           WritableImage image = anchorPane.snapshot(sp, null);

           File file = new File("C:\\Users\\Public\\Pictures\\" + fileName.getText() + ".png");


           ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//end pic

           firstNameF.setText("");
           lastNameF.setText("");
           phoneF.setText("");
           emailF.setText("");
           streetF.setText("");
           cityF.setText("");
           stateF.setText("");
           zipF.setText("");
           costF.setText("");
           typeF.setText("");
           dueDate.setText("");
           salesPerson.setText("");
           dateF.setText("");
           setButton.setDisable(false);
           exportButton.setDisable(true);
           fileName.setDisable(true);
           fileName.setText("");

           f1.setText("");
           f2.setText("");
           f3.setText("");
           f4.setText("");
           f5.setText("");
           f6.setText("");
           f7.setText("");
           f8.setText("");
           f9.setText("");
           f10.setText("");
           f11.setText("");
           f12.setText("");
           f13.setText("");
           f14.setText("");
           f15.setText("");
           f16.setText("");
           f17.setText("");
           f18.setText("");
           f19.setText("");
           f20.setText("");
           f21.setText("");
           f22.setText("");
           f23.setText("");
           f24.setText("");
           f25.setText("");
           f26.setText("");
           f27.setText("");
           f28.setText("");
           f29.setText("");
           f30.setText("");
        activeTable.getSelectionModel().clearSelection();
    }//end export

    @FXML
    private void setData(ActionEvent actionEvent) throws IOException {
        //get selected
        LocalDate currentdate = LocalDate.now();

        DataStore person = activeTable.getSelectionModel().getSelectedItem();
        firstNameF.setText(person.getFirstName());
        lastNameF.setText(person.getLastName());
        phoneF.setText(person.getPhone());
        emailF.setText(person.getEmail());
        streetF.setText(person.getStreet());
        cityF.setText(person.getCity());
        stateF.setText(person.getState());
        zipF.setText(person.getZip());
        costF.setText("$"+person.getJobCost());
        typeF.setText(person.getJobType());
        setButton.setDisable(true);
        exportButton.setDisable(false);
        fileName.setDisable(false);
        fileName.setText(person.getFirstName()+person.getLastName()+ currentdate.getMonth()+currentdate.getYear());
    }// end selection


}//end of program