package Reports;
import Dashboard.DataStore3;
import Dashboard.DataStore4;
import Dashboard.DatabaseConnection;
import Reports.CustomerReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class badMonthController extends DatabaseConnection implements Initializable{

    @FXML
    private TableView<DataStore4> activeTable4;
    @FXML
    private TableColumn<DataStore4, String> col_year;
    @FXML
    private TableColumn<DataStore4, String> col_month;
    @FXML
    private TableColumn<DataStore4, String> col_count;

    ObservableList<DataStore4> activeList4 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("select year(date_start) AS 'Year',date_format(date_start,'%M') AS Month,COUNT(month(date_start)) AS 'Count' from Job group by year(date_start),month(date_start),MonthName(date_start) order by year(date_start),month(date_start)");
            while (rs.next()) {
                activeList4.add(new DataStore4(rs.getString("Year"), rs.getString("Month"), rs.getString("Count")));
            }
            disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(badMonthController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_count.setCellValueFactory(new PropertyValueFactory<>("count"));

        activeTable4.setItems(activeList4);

    }

    //buttons
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