package Jobs;

import Cust.CustJobsMainController;
import Cust.CustSearchController;
import Dashboard.DatabaseConnection;
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
    private JFXComboBox jobType;
    @FXML
    private JFXComboBox paymentType;
    @FXML
    private JFXComboBox jobStatus;
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

        Connection connection = null;

        try {

            connection = getConnectionPlain();
            ResultSet rs = connection.createStatement().executeQuery("SELECT job_sketch FROM Job ORDER BY job_id DESC LIMIT 1");

            if (rs.next()) {
                InputStream is = rs.getBinaryStream("job_sketch");

                // instead of the next 9 lines, you could just do
                // javafx.scene.image.Image image1 = new Image(is);

                OutputStream os = new FileOutputStream(new File("img.png"));
                byte[] content = new byte[1024];
                int size = 0;


                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }

                os.close();
                is.close();
                File file =new File("img.png");
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
                sketchView.setImage(image1);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException Finally: - " + e);
            }
        }
    }
        @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJob.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void diffSceneCustID(Object change, int edit){
        this.x = change;
        this.z = edit;
    }

    public void showInfo(String jType, String jCost, String jStatus, String jPayment, String jStart, String jFinish){
        jobType.setValue(jType);
        cost.setText(jCost);
        jobStatus.setValue(jStatus);
        paymentType.setValue(jPayment);
        startDate.setValue(LocalDate.parse(jStart));
        fDate.setValue(LocalDate.parse(jFinish));
    }

    @FXML
    public void changeToActiveJobs(ActionEvent actionEvent) throws IOException {
        if (z  == 1) {
            try {
                Statement sqlInsert = ConnectToDatabase();
                DecimalFormat df = new DecimalFormat("###,###,###.00");
                double num = Double.parseDouble(cost.getText());
                sqlInsert.execute("INSERT INTO Job(customer_id, job_type, job_cost, job_status, date_start, date_complete, payment_type) VALUES ('"+x+"' ,'" + jobType.getValue() + "','" + df.format(num) + "','" + jobStatus.getValue() + "','" + startDate.getValue().toString() + "','" + fDate.getValue().toString() + "','" + paymentType.getValue() + "')");
                disconnectFromDB(sqlInsert);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            jobType.setValue(null);
            cost.clear();
            jobStatus.setValue(null);
            startDate.setValue(null);
            fDate.setValue(null);
            paymentType.setValue(null);
        } else if (z == 2) {
            try {
                Statement sqlUpdate = ConnectToDatabase();
                /*
                DecimalFormat df = new DecimalFormat("###,###,###.00");
                double num = Double.parseDouble(cost.getText());
                 */
                sqlUpdate.execute("UPDATE Job SET job_type = '"+jobType.getValue()+"', job_cost = '"+cost.getText()+"', job_status = '"+jobStatus.getValue()+"', date_start = '"+startDate.getValue().toString()+"', date_complete = '"+fDate.getValue().toString()+"', payment_type = '"+paymentType.getValue()+"' WHERE job_id = '"+x+"'");
                disconnectFromDB(sqlUpdate);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            jobType.setValue(null);
            cost.clear();
            jobStatus.setValue(null);
            startDate.setValue(null);
            fDate.setValue(null);
            paymentType.setValue(null);
        } else {
            try {
                Statement sqlInsert = ConnectToDatabase();
                DecimalFormat df = new DecimalFormat("###,###,###.00");
                double num = Double.parseDouble(cost.getText());
                sqlInsert.execute("INSERT INTO Job(customer_id, job_type, job_cost, job_status, date_start, date_complete, payment_type) VALUES ((SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1) ,'" + jobType.getValue() + "','" + df.format(num) + "','" + jobStatus.getValue() + "','" + startDate.getValue().toString() + "','" + fDate.getValue().toString() + "','" + paymentType.getValue() + "')");
                disconnectFromDB(sqlInsert);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            jobType.setValue(null);
            cost.clear();
            jobStatus.setValue(null);
            startDate.setValue(null);
            fDate.setValue(null);
            paymentType.setValue(null);
        }
        browseBtn.setDisable(false);
    }

    @FXML
    private void BrowseFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG")
        );
        selectedFile = fc.showOpenDialog(null);
         filename = selectedFile.getAbsolutePath();
        filePath.setText(filename);

    }

    @FXML
    private void submitPhoto(ActionEvent actionEvent) {
        //Start of image saving. note requires that it uses prepared statement
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream inputStream = null;

        if (z == 2) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
                sketchView.setImage(image1);
                File image = new File(filename);
                inputStream = new FileInputStream(image);

                connection = getConnectionPlain();
                statement = connection.prepareStatement("UPDATE Job " + "SET job_sketch = (?) WHERE job_id = '"+x+"'");
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
        } else {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
                sketchView.setImage(image1);
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
        }
    }
}
