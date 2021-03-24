package CustAndJobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustEditController {
    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustJobsMain.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void editAndSave(ActionEvent actionEvent) {
//Needs to be done
    }

    @FXML
    private void viewJob(ActionEvent actionEvent) {
//Needs to be done
    }

    @FXML
    private void jobComplete(ActionEvent actionEvent) {
//Needs to be done
    }
}
