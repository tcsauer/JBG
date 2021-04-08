package Dashboard;

import Reports.unpaidJobsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import SysSettings.*;


import Dashboard.DataStore6;
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
import javafx.util.Duration;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class dashboardController extends DatabaseConnection implements Initializable{

    @FXML
    private void changeToSystem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SysSettings/settings.fxml"));
        Parent root = loader.load();

        //How to pass variables between scenes

        //Get controller of scene2
        settingsController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        scene2Controller.test("Wow", "Such Test");


        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Tableview uncompleted jobs
    @FXML
    private TableView<DataStore6> activeTable7;

    @FXML
    private TableView<DataStore6> activeTable6;
    @FXML
    private TableColumn<DataStore6, String> col_fname;
    @FXML
    private TableColumn<DataStore6, String> col_lname;
    @FXML
    private TableColumn<DataStore6, String> col_phone;
    @FXML
    private TableColumn<DataStore6, String> col_type;
    @FXML
    private TableColumn<DataStore6, String> col_cost;
    @FXML
    private TableColumn<DataStore6, String> col_status;
    @FXML
    private TableColumn<DataStore6, String> col_date;

    ObservableList<DataStore6> activeList6 = FXCollections.observableArrayList();


    @FXML Label date;
    @FXML Label revenue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT Customer.cust_fname, Customer.cust_lname, Customer.cust_phone, Job.job_type, Job.job_cost, Job.job_status, Job.date_start FROM Customer JOIN Job ON Job.customer_id = Customer.customer_id WHERE Job.job_status = 'Pending';");
            while (rs.next()) {
                activeList6.add(new DataStore6(rs.getString("Customer.cust_fname"), rs.getString("Customer.cust_lname"), rs.getString("Customer.cust_phone"), rs.getString("Job.job_type"), rs.getString("Job.job_cost"), rs.getString("Job.job_status"), rs.getString("Job.date_start")));
            }

            ResultSet rs2 = con.executeQuery("SELECT sum(job_cost) as count  FROM Job WHERE MONTH(date_complete) = MONTH(CURRENT_DATE()) AND YEAR(date_complete) = YEAR(CURRENT_DATE());");
            while (rs2.next()) {
                revenue.setText("$" + rs2.getString("count"));

            }

            disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        activeTable6.setItems(activeList6);

//date and time test
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        date.setText(timeStamp);
//end date and time
    }

    @FXML
    private void changeToCust(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cust/CustJobsMain.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToJobs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Jobs/AllJob.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void changeToReports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports/report.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void changeToQuickInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuickInvoice/quickInvoice.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
