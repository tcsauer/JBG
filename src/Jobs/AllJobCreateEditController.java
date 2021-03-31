package Jobs;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.logging.Logger;

public class AllJobCreateEditController {
    @FXML
    private JFXButton deleteBtn;

    @FXML
    private Button browseBtn;

    @FXML
    private ImageView sketchView;

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
