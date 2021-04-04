package Reports;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerReportController {

    @FXML
    private void changeToDash(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void printReport(ActionEvent actionEvent) {
//Needs to be Started
    }

    @FXML
    private void ExportReport(ActionEvent actionEvent) {
//Needs to be started
    }
}