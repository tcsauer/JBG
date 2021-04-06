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
    private void customerInfo(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Reports/CustomerReport.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void pastJobs(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Reports/pastJobs.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void unpaidJobs(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Reports/unpaidJobs.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void badMonth(ActionEvent event) throws IOException {
        Parent SceneParent = FXMLLoader.load(getClass().getResource("/Reports/badMonth.fxml"));
        Scene newScene = new Scene(SceneParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
