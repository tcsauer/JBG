package Jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActiveJobController {
    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("../Cust/CustJobsMain.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToViewJobs(ActionEvent actionEvent) throws IOException {
//Needs to be done



        Parent SceneParent = FXMLLoader.load(getClass().getResource("JobCreateEdit.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void printJob(ActionEvent actionEvent) {
    }

    @FXML
    private void completeJob(ActionEvent actionEvent) {
    }
}
