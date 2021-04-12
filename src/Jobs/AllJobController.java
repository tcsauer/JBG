package Jobs;

import Cust.SaveCust;
import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import QuickInvoice.quickInvoiceController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.scripts.JO;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllJobController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<Jobs> AllJobsTable;
    @FXML
    public TableColumn<Jobs,String> col_lname;
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
    @FXML
    public ComboBox jobFilter, paymentFilter;
    @FXML
    private JFXCheckBox activeFilter;

    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    @FXML
    private Label errLab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_id, cust_lname, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job J JOIN Customer C on C.customer_id = J.customer_id");

            while(rs.next()){
                jobList.add(new Jobs(rs.getInt("job_id"), rs.getString("cust_lname"), rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start"),rs.getString("date_complete"),rs.getString("payment_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(AllJobController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_lname.setCellValueFactory(new PropertyValueFactory<>("cust_lname"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        col_dateComplete.setCellValueFactory(new PropertyValueFactory<>("dateComplete"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        AllJobsTable.setItems(jobList);

        jobFilter.getItems().addAll("Drapes",
                "Window Treatment",
                "Couch",
                "Chair(s)",
                "Custom");
        paymentFilter.getItems().addAll("Card",
                "Cash",
                "Check");
    }

    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void newJob(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backup_scenes/custSearchAndReturn.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void filterActive(ActionEvent actionEvent) throws IOException {
        if(activeFilter.isSelected()) {
            FilteredList<Jobs> activeItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(activeItems);

            Predicate<Jobs> active = i -> i.getJobStatus().contains("Pending");
            activeItems.setPredicate(active);
        }else{
            AllJobsTable.setItems(jobList);
        }
    }

    @FXML
    private void filterJob(ActionEvent actionEvent) throws IOException {
        if(jobFilter.getValue() == "Drapes"){
            FilteredList<Jobs> jobItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(jobItems);

            Predicate<Jobs> type1 = i -> i.getJobType().contains("Drapes");
            jobItems.setPredicate(type1);
        }else if(jobFilter.getValue() == "Window Treatment"){
            FilteredList<Jobs> jobItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(jobItems);

            Predicate<Jobs> type1 = i -> i.getJobType().contains("Window Treatment");
            jobItems.setPredicate(type1);
        }else if(jobFilter.getValue() == "Couch"){
            FilteredList<Jobs> jobItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(jobItems);

            Predicate<Jobs> type1 = i -> i.getJobType().contains("Couch");
            jobItems.setPredicate(type1);
        }else if(jobFilter.getValue() == "Custom"){
            FilteredList<Jobs> jobItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(jobItems);

            Predicate<Jobs> type1 = i -> i.getJobType().contains("Custom");
            jobItems.setPredicate(type1);
        }else{
            FilteredList<Jobs> jobItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(jobItems);

            Predicate<Jobs> type1 = i -> i.getJobType().contains("Chair(s)");
            jobItems.setPredicate(type1);
        }
    }

    @FXML
    private void filterPayment(ActionEvent actionEvent) throws IOException{
        if(paymentFilter.getValue() == "Card"){
            FilteredList<Jobs> paymentItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(paymentItems);

            Predicate<Jobs> type2 = i -> i.getPaymentType().contains("Card");
            paymentItems.setPredicate(type2);
        }else if(paymentFilter.getValue() == "Cash"){
            FilteredList<Jobs> paymentItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(paymentItems);

            Predicate<Jobs> type2 = i -> i.getPaymentType().contains("Cash");
            paymentItems.setPredicate(type2);
        }else{
            FilteredList<Jobs> paymentItems = new FilteredList<Jobs>(jobList);
            AllJobsTable.setItems(paymentItems);

            Predicate<Jobs> type2 = i -> i.getPaymentType().contains("Check");
            paymentItems.setPredicate(type2);
        }
    }

    @FXML
    private void deleteJob(ActionEvent actionEvent) throws IOException{
        if(AllJobsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Statement sqlDelete = ConnectToDatabase();
                sqlDelete.execute("DELETE FROM Job WHERE job_id = '" + AllJobsTable.getSelectionModel().getSelectedItem().getJobID() + "'");
                disconnectFromDB(sqlDelete);
                AllJobsTable.getItems().removeAll(AllJobsTable.getSelectionModel().getSelectedItem());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            errLab.setVisible(true);
        }
    }

    @FXML
    private void editJob(ActionEvent actionEvent) throws IOException{
        if(AllJobsTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jobs/AllJobCreateEdit.fxml"));
            Parent root = loader.load();
            AllJobCreateEditController editScene = loader.getController();
            editScene.diffSceneCustID(AllJobsTable.getSelectionModel().getSelectedItem().getJobID(), 2);
            editScene.showInfo(AllJobsTable.getSelectionModel().getSelectedItem().getJobType(), AllJobsTable.getSelectionModel().getSelectedItem().getJobCost(), AllJobsTable.getSelectionModel().getSelectedItem().getJobStatus(), AllJobsTable.getSelectionModel().getSelectedItem().getPaymentType(), AllJobsTable.getSelectionModel().getSelectedItem().getDateStart(), AllJobsTable.getSelectionModel().getSelectedItem().getDateComplete());
            editScene.showImage(AllJobsTable.getSelectionModel().getSelectedItem().getJobSketch());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }else{
            errLab.setVisible(true);
        }
    }

    @FXML
    private void clearError(MouseEvent mouseEvent) {
        errLab.setVisible(false);
    }

    @FXML
    private void reset(ActionEvent actionEvent) {
        jobFilter.setValue(null);
        if(jobFilter.getValue() == null){
            AllJobsTable.setItems(jobList);
        }
        paymentFilter.setValue(null);
        if(paymentFilter.getValue() == null){
            AllJobsTable.setItems(jobList);
        }
        activeFilter.setSelected(false);
    }
}
