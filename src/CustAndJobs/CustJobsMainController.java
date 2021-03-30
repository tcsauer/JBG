package CustAndJobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustJobsMainController {
    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToActiveJob(ActionEvent actionEvent) throws IOException {
        Parent newParent = FXMLLoader.load(getClass().getResource("ActiveJob.fxml"));
        Scene newScene = new Scene(newParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToCustSearch(ActionEvent actionEvent) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("CustSearch.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void saveForm(ActionEvent actionEvent) {
//Needs to be done
    }

    @FXML
    private void saveAndChangeToJob(ActionEvent actionEvent) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("JobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();



//Needs to be done
    }
}
