package Jobs;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JobCustCreateController {
    @FXML
    private JFXButton printBtn;

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("../Cust/CustJobsMain.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void changeToActiveJobs(ActionEvent actionEvent) throws IOException {
//NEEDS TO BE DONE
        //Not sure if user should be transitioning scenes here




        printBtn.setVisible(true); //Print button should be hidden unless fields are populated

        Parent SceneParent = FXMLLoader.load(getClass().getResource("ActiveJob.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void PrintJob(ActionEvent actionEvent) {
    }
}
