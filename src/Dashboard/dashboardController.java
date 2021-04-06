package Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import SysSettings.*;

import java.io.IOException;

public class dashboardController {

    @FXML
    private void changeToSystem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SysSettings/settings.fxml"));
        Parent root = loader.load();

        //How to pass variables between scenes

        //Get controller of scene2
        settingsController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        scene2Controller.test("Wow", "Such Test");


        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToCust(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cust/CustJobsMain.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToJobs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Jobs/AllJob.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void changeToReports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports/report.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void changeToQuickInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuickInvoice/quickInvoice.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
