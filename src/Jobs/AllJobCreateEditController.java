package Jobs;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AllJobCreateEditController {
    @FXML
    private JFXButton deleteBtn;

    @FXML
    private Button browseBtn;

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

        if(selectedFile != null) {
            System.out.println(selectedFile.getName());
        }else {
            System.out.println("File is not valid");
        }
    }
}
