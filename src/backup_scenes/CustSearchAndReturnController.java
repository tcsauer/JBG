package backup_scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustSearchAndReturnController {
    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustSearchForJobs.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void viewCstProfile(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJobCreateEdit.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void custSearch(ActionEvent actionEvent) {
    }
}