package Jobs;

import Dashboard.DatabaseConnection;
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
import java.io.IOException;
import java.net.URL;
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
    private JFXComboBox<String> jobType;
    @FXML
    private JFXComboBox<String> paymentType;
    @FXML
    private JFXComboBox<String> jobStatus;
    @FXML
    private JFXTextField cost;
    @FXML
    private JFXDatePicker fDate;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXTextField filePath;

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
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("AllJob.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToActiveJobs(ActionEvent actionEvent) throws IOException {
//NEEDS TO BE DONE

    }

    @FXML
    private void DeleteJob(ActionEvent actionEvent) {
    }

    @FXML
    private void BrowseFile(ActionEvent actionEvent){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG")
        );
        File selectedFile = fc.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            sketchView.setImage(image);
            System.out.println(selectedFile.getName());
        } catch (IOException ex) {
            System.out.println("File is not valid");
        }
    }
}
