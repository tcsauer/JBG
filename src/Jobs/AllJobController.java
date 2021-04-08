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
    public TableColumn<Jobs,byte[]> col_sketch;
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
    private JFXComboBox jobFilter, paymentFilter;
    @FXML
    private JFXCheckBox activeFilter;


    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_id, job_sketch, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job");

            while(rs.next()){
                jobList.add(new Jobs(rs.getInt("job_id"), rs.getBytes("job_sketch"),rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start"),rs.getString("date_complete"),rs.getString("payment_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(AllJobController.class.getName()).log(Level.SEVERE,null,ex);
        }
        col_sketch.setCellValueFactory(new PropertyValueFactory<>("jobSketch"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        col_dateComplete.setCellValueFactory(new PropertyValueFactory<>("dateComplete"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        AllJobsTable.setItems(jobList);
        AllJobsTable.setEditable(true);

        ObservableList<String> jobTypeList = FXCollections.observableArrayList();
        jobTypeList.addAll(
             "Drapes",
             "Window Treatment",
             "Couch",
             "Chair(s)"
        );
        col_type.setCellFactory(ComboBoxTableCell.forTableColumn(jobTypeList));
        col_cost.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<String> jobStatusList = FXCollections.observableArrayList();
        jobStatusList.addAll(
                "Complete",
                "Pending"
        );
        col_status.setCellFactory(ComboBoxTableCell.forTableColumn(jobStatusList));
        col_dateStart.setCellFactory(TextFieldTableCell.forTableColumn());
        col_dateComplete.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<String> paymentTypeList = FXCollections.observableArrayList();
        paymentTypeList.addAll(
                "Card",
                "Cash",
                "Check"
        );
        col_payment.setCellFactory(ComboBoxTableCell.forTableColumn(paymentTypeList));
        jobFilter.getItems().addAll("Drapes",
                "Window Treatment",
                "Couch",
                "Chair(s)");
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

    JOptionPane frame = new JOptionPane();

    public void changeJobTypeCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected.getJobID();
        jobSelected.setJobType(editedCell.getNewValue().toString());
        String newJobType = jobSelected.getJobType();
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET job_type= '" + newJobType + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    public void changeJobCostCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected1 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected1.getJobID();
        jobSelected1.setJobCost(editedCell.getNewValue().toString());
        String newJobCost = jobSelected1.getJobCost();
        if (newJobCost == null || newJobCost.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                DecimalFormat x = new DecimalFormat("###,###,###.00");
                double num = Double.parseDouble(newJobCost);
                sqlUpdate.execute("UPDATE Job SET job_cost= '" + x.format(num) + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void changeJobStatusCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected2 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected2.getJobID();
        jobSelected2.setJobStatus(editedCell.getNewValue().toString());
        String newJobStatus = jobSelected2.getJobStatus();
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET job_status= '" + newJobStatus + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    public void changeDateStartCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected3 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected3.getJobID();
        jobSelected3.setDateStart(editedCell.getNewValue().toString());
        String newDateStart = jobSelected3.getDateStart();
        if (newDateStart == null || newDateStart.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET date_start = '" + newDateStart + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void changeDateCompleteCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected4 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected4.getJobID();
        jobSelected4.setDateComplete(editedCell.getNewValue().toString());
        String newDateComplete = jobSelected4.getDateComplete();
        if (newDateComplete == null || newDateComplete.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET date_complete = '" + newDateComplete + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void changePaymentTypeCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected5 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Jobs>() {
            @Override
            public void onChanged(Change<? extends Jobs> c) {
                for (Jobs s : c.getList()) {
                    System.out.println(s.getJobID());
                }
            }
        });
        int tempID = jobSelected5.getJobID();
        jobSelected5.setPaymentType(editedCell.getNewValue().toString());
        String newPaymentType = jobSelected5.getPaymentType();
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET payment_type = '" + newPaymentType + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
        try {
            Statement sqlDelete = ConnectToDatabase();
            sqlDelete.execute("DELETE FROM Job WHERE job_id = '"+AllJobsTable.getSelectionModel().getSelectedItem().getJobID()+"'");
            disconnectFromDB(sqlDelete);
            AllJobsTable.getItems().removeAll(AllJobsTable.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
