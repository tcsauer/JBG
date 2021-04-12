package Jobs;

import Cust.CustJobsMainController;
import Cust.CustSearchController;
import Dashboard.DatabaseConnection;
import Dashboard.Validation;
import backup_scenes.CustSearchAndReturnController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AllJobCreateEditController extends DatabaseConnection implements Initializable {
    @FXML
    private JFXButton deleteBtn, submitBtn;

    @FXML
    private Button browseBtn;

    @FXML
    private ImageView sketchView;
    @FXML
    private ComboBox jobType;
    @FXML
    private ComboBox paymentType;
    @FXML
    private ComboBox jobStatus;
    @FXML
    private JFXTextField cost;
    @FXML
    private JFXDatePicker fDate;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXTextField filePath;

    private Object x;
    private int z;
    File selectedFile;
    String filename;
    @FXML
    private JFXButton submitPhoto;
    @FXML
    private Label goodLab;
    @FXML
    private Label goodLab2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        browseBtn.setDisable(true);
        submitPhoto.setDisable(true);

        jobType.getItems().addAll(
                "Drapes",
                "Window Treatment",
                "Couch",
                "Chair(s)",
                "Custom"
        );

        paymentType.getItems().addAll(
                "Card",
                "Cash",
                "Check"
        );

        jobStatus.getItems().addAll(
                "Complete",
                "Pending"
        );
    }

    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJob.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void diffSceneCustID(Object change, int edit) {
        this.x = change;
        this.z = edit;
    }

    public void showInfo(String jType, String jCost, String jStatus, String jPayment, String jStart, String jFinish) {
        jobType.setValue(jType);
        cost.setText(jCost);
        jobStatus.setValue(jStatus);
        paymentType.setValue(jPayment);
        startDate.setValue(LocalDate.parse(jStart));
        fDate.setValue(LocalDate.parse(jFinish));
    }

    public void showImage(byte[] png) throws IOException {
        Connection connection = null;
        File f = File.createTempFile("img", ".png");
        try {
            connection = getConnectionPlain();
            ResultSet rs = connection.createStatement().executeQuery("SELECT job_sketch FROM Job WHERE job_id = '" + x + "'");
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("job_sketch");
                if(is != null){
                    OutputStream os = new FileOutputStream(f.getAbsolutePath());
                    png = new byte[1024];
                    int size = 0;

                    while ((size = is.read(png)) != -1) {

                        os.write(png, 0, size);
                    }

                    os.close();
                    is.close();
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
                    sketchView.setImage(image1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        f.deleteOnExit();
    }

    @FXML
    public void changeToActiveJobs(ActionEvent actionEvent) throws IOException {
        goodLab2.setVisible(false);
        if (z  == 1) {
            if(Validation.comboBoxNotEmpty(jobType, paymentType, jobStatus))
            if(Validation.datePickerNotEmpty(startDate, fDate))
            if(Validation.validDate(startDate, fDate))
            if(Validation.costFieldNotEmpty(cost))
            if(Validation.costFormat(cost)){
                try {
                    Statement sqlInsert = ConnectToDatabase();
                    sqlInsert.execute("INSERT INTO Job(customer_id, job_type, job_cost, job_status, date_start, date_complete, payment_type) VALUES ('"+x+"' ,'" + jobType.getValue() + "','" + cost.getText() + "','" + jobStatus.getValue() + "','" + startDate.getValue().toString() + "','" + fDate.getValue().toString() + "','" + paymentType.getValue() + "')");
                    disconnectFromDB(sqlInsert);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                jobType.getSelectionModel().clearSelection();
                jobType.setValue(null);
                jobType.setPromptText("Job Type");
                cost.clear();
                jobStatus.getSelectionModel().clearSelection();
                jobStatus.setValue(null);
                jobStatus.setPromptText("Job Status");
                startDate.setValue(null);
                fDate.setValue(null);
                paymentType.getSelectionModel().clearSelection();
                paymentType.setValue(null);
                paymentType.setPromptText("Payment Type");
                goodLab.setVisible(true);
            }
        } else if (z == 2) {
            if(Validation.comboBoxNotEmpty(jobType, paymentType, jobStatus))
            if(Validation.datePickerNotEmpty(startDate, fDate))
            if(Validation.validDate(startDate, fDate))
            if(Validation.costFieldNotEmpty(cost))
            if(Validation.costFormat(cost)){
                try {
                    Statement sqlUpdate = ConnectToDatabase();
                    sqlUpdate.execute("UPDATE Job SET job_type = '"+jobType.getValue()+"', job_cost = '"+cost.getText()+"', job_status = '"+jobStatus.getValue()+"', date_start = '"+startDate.getValue().toString()+"', date_complete = '"+fDate.getValue().toString()+"', payment_type = '"+paymentType.getValue()+"' WHERE job_id = '"+x+"'");
                    disconnectFromDB(sqlUpdate);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                jobType.getSelectionModel().clearSelection();
                jobType.setValue(null);
                jobType.setPromptText("Job Type");
                cost.clear();
                jobStatus.getSelectionModel().clearSelection();
                jobStatus.setValue(null);
                jobStatus.setPromptText("Job Status");
                startDate.setValue(null);
                fDate.setValue(null);
                paymentType.getSelectionModel().clearSelection();
                paymentType.setValue(null);
                paymentType.setPromptText("Payment Type");
                goodLab.setVisible(true);
            }
        } else {
            if(Validation.comboBoxNotEmpty(jobType, paymentType, jobStatus))
            if(Validation.datePickerNotEmpty(startDate, fDate))
            if(Validation.validDate(startDate, fDate))
            if(Validation.costFieldNotEmpty(cost))
            if(Validation.costFormat(cost)){
                try {
                    Statement sqlInsert = ConnectToDatabase();
                    sqlInsert.execute("INSERT INTO Job(customer_id, job_type, job_cost, job_status, date_start, date_complete, payment_type) VALUES ((SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1) ,'" + jobType.getValue() + "','" + cost.getText() + "','" + jobStatus.getValue() + "','" + startDate.getValue().toString() + "','" + fDate.getValue().toString() + "','" + paymentType.getValue() + "')");
                    disconnectFromDB(sqlInsert);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                jobType.getSelectionModel().clearSelection();
                jobType.setValue(null);
                jobType.setPromptText("Job Type");
                cost.clear();
                jobStatus.getSelectionModel().clearSelection();
                jobStatus.setValue(null);
                jobStatus.setPromptText("Job Status");
                startDate.setValue(null);
                fDate.setValue(null);
                paymentType.getSelectionModel().clearSelection();
                paymentType.setValue(null);
                paymentType.setPromptText("Payment Type");
                goodLab.setVisible(true);
            }
        }
        browseBtn.setDisable(false);
    }

    @FXML
    private void BrowseFile(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG")
        );
        selectedFile = fc.showOpenDialog(null);
        filename = selectedFile.getAbsolutePath();
        if (selectedFile != null) {
            filePath.setText(filename);
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            sketchView.setImage(image1);
            submitPhoto.setDisable(false);
            goodLab.setVisible(false);
        }

    }

    @FXML
    private void submitPhoto(ActionEvent actionEvent) {
        //Start of image saving. note requires that it uses prepared statement
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream inputStream = null;
        if(selectedFile != null){
            if (z == 2) {
                try {
                    File image = new File(filename);
                    inputStream = new FileInputStream(image);

                    connection = getConnectionPlain();
                    statement = connection.prepareStatement("UPDATE Job " + "SET job_sketch = (?) WHERE job_id = '" + x + "'");
                    statement.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));

                    statement.executeUpdate();
                    System.out.println("image uploaded");

                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFoundException: - " + e);
                } catch (SQLException e) {
                    System.out.println("SQLException: - " + e);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        connection.close();
                        statement.close();
                    } catch (SQLException e) {
                        System.out.println("SQLException Finally: - " + e);
                    }
                }
            } else {
                try {
                    File image = new File(filename);
                    inputStream = new FileInputStream(image);

                    connection = getConnectionPlain();
                    statement = connection.prepareStatement("UPDATE Job " + "SET job_sketch = (?) ORDER BY job_id DESC LIMIT 1");
                    statement.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));

                    statement.executeUpdate();

                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFoundException: - " + e);
                } catch (SQLException e) {
                    System.out.println("SQLException: - " + e);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        connection.close();
                        statement.close();
                    } catch (SQLException e) {
                        System.out.println("SQLException Finally: - " + e);
                    }
                }
                filePath.setText("Filepath");
                sketchView.setImage(null);
                goodLab2.setVisible(true);
                browseBtn.setDisable(true);
                submitPhoto.setDisable(true);
            }
        }
    }
}
