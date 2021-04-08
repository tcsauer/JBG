package Jobs;

import Cust.SaveCust;
import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import QuickInvoice.quickInvoiceController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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

    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_id, job_sketch, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job");

            while(rs.next()){
                InputStream in = rs.getBinaryStream("job_sketch");
                /*
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = in.read(content)) != -1){
                    os.write(content, 0,size);
                }
                os.close();
                in.close();

                Image image = new Image("file:photo.jpg",100,150,true,true);
                ImageView imageView1 = new ImageView(image);
                imageView1.setFitWidth(100);
                imageView1.setFitHeight(150);
                imageView1.setPreserveRatio(true);
                //Image image = new Image(in);
                */
                jobList.add(new Jobs(rs.getInt("job_id"), rs.getBytes("job_sketch"),rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start"),rs.getString("date_complete"),rs.getString("payment_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(AllJobController.class.getName()).log(Level.SEVERE,null,ex);
            //System.out.println(ex.getMessage());
        }
        col_sketch.setCellValueFactory(new PropertyValueFactory<>("jobSketch"));
        /*col_sketch.setCellFactory(param -> new TableCell<Jobs, byte[]>() {

            private ImageView imageView = new ImageView();

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(getImageFromBytes(item));
                    setGraphic(imageView);
                }
                this.setItem(item);
            }
        });*/
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
    }
/*
    private Image getImageFromBytes(byte[] imgBytes) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imgBytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
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
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        if (newJobType == null || newJobType.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
            //jobSelected.setJobType(editedCell.getOldValue().toString());
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET job_type= '" + newJobType + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void changeJobCostCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected1 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
            //jobSelected.setJobType(editedCell.getOldValue().toString());
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
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        if (newJobStatus == null || newJobStatus.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
            //jobSelected.setJobType(editedCell.getOldValue().toString());
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET job_status= '" + newJobStatus + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void changeDateStartCellEvent(TableColumn.CellEditEvent editedCell) {
        Jobs jobSelected3 = (Jobs) AllJobsTable.getSelectionModel().getSelectedItem();
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
            //jobSelected.setJobType(editedCell.getOldValue().toString());
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
            //jobSelected.setJobType(editedCell.getOldValue().toString());
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
        AllJobsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        if (newPaymentType == null || newPaymentType.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Cell cannot be empty");
            //jobSelected.setJobType(editedCell.getOldValue().toString());
        } else {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                sqlUpdate.execute("UPDATE Job SET payment_type = '" + newPaymentType + "' WHERE job_id = '" + tempID + "'");
                disconnectFromDB(sqlUpdate);
                JOptionPane.showMessageDialog(frame, "Updated");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    @FXML
    private void changeToViewJobs(ActionEvent actionEvent) throws IOException {
//Needs to be done


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJobCreateEdit.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void printJob(ActionEvent actionEvent) {
    }

    @FXML
    private void completeJob(ActionEvent actionEvent) {
    }

    @FXML
    private void newJob(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backup_scenes/custSearchAndReturn.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
