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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AllJobCreateEditController extends DatabaseConnection implements Initializable {
    @FXML
    private JFXButton deleteBtn;

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

    private boolean sceneCheck = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobType.getItems().addAll(
                "Drapes",
                "Window Treatment",
                "Couch",
                "Chairs(s)"
        );

        jobType.setEditable(true);

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
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToActiveJobs(ActionEvent actionEvent) throws IOException {
            try {
                System.out.println("Yes");
                Statement sqlInsert = ConnectToDatabase();
                DecimalFormat x = new DecimalFormat("###,###,###.00");
                double num = Double.parseDouble(cost.getText());
                //sqlInsert.execute("INSERT INTO Job(customer_id, job_type, job_cost, job_status, date_start, date_complete, payment_type) VALUES ((SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1) ,'" + jobType.getValue() + "','" + x.format(num) + "','" + jobStatus.getValue() + "','" + startDate.getValue().toString() + "','" + fDate.getValue().toString() + "','" + paymentType.getValue() + "')");
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

    @FXML
    private void DeleteJob(ActionEvent actionEvent) {
        try {
            Statement sqlDelete = ConnectToDatabase();
            sqlDelete.execute("DELETE FROM Customer ORDER BY customer_id DESC LIMIT 1");
            disconnectFromDB(sqlDelete);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        deleteBtn.setDisable(true);
        jobType.setValue(null);
        cost.clear();
        jobStatus.setValue(null);
        startDate.setValue(null);
        fDate.setValue(null);
        paymentType.setValue(null);
    }

    @FXML
    private void BrowseFile(ActionEvent actionEvent){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG")
        );
        File selectedFile = fc.showOpenDialog(null);
        String filename = selectedFile.getAbsolutePath();
        filePath.setText(filename);

        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            sketchView.setImage(image);
            Statement sqlInsert = ConnectToDatabase();
            InputStream inputS = new FileInputStream(new File(filename));
            sqlInsert.execute("UPDATE Job " + "SET job_sketch = ('" + inputS + "') ORDER BY job_id DESC LIMIT 1");
            disconnectFromDB(sqlInsert);
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
