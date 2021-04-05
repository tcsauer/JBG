package Reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class reportController {
    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
