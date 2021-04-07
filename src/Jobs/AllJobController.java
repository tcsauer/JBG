package Jobs;

import Dashboard.DataStore;
import Dashboard.DatabaseConnection;
import QuickInvoice.quickInvoiceController;
import SysSettings.settingsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllJobController extends DatabaseConnection implements Initializable {

    @FXML
    private TableView<Jobs> AllJobsTable;
    @FXML
    public TableColumn<Jobs,byte[]> col_sketch;
    @FXML
    private TableColumn<Jobs,String> col_type;
    @FXML
    private TableColumn<Jobs,String> col_cost;
    @FXML
    private TableColumn<Jobs,String> col_status;
    @FXML
    private TableColumn<Jobs, String> col_dateStart;
    @FXML
    private TableColumn<Jobs, String> col_dateComplete;
    @FXML
    private TableColumn<Jobs, String> col_payment;

    ObservableList<Jobs> jobList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement con = ConnectToDatabase();
            ResultSet rs = con.executeQuery("SELECT job_sketch, job_type, job_cost, job_status, date_start, date_complete, payment_type FROM Job");

            while(rs.next()){
                InputStream in = rs.getBinaryStream("job_sketch");
                /*
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = in.read(content)) != -1){
                    os.write(content, 0,size);
                }
                os.close();
                in.close();

                Image image = new Image("file:photo.jpg",100,150,true,true);
                ImageView imageView1 = new ImageView(image);
                imageView1.setFitWidth(100);
                imageView1.setFitHeight(150);
                imageView1.setPreserveRatio(true);
                //Image image = new Image(in);
                */
                jobList.add(new Jobs(rs.getBytes("job_sketch"),rs.getString("job_type"),rs.getString("job_cost"),rs.getString("job_status"),rs.getString("date_start"),rs.getString("date_complete"),rs.getString("payment_type")));
            }disconnectFromDB(con);
        } catch (Exception ex) {
            Logger.getLogger(AllJobController.class.getName()).log(Level.SEVERE,null,ex);
            //System.out.println(ex.getMessage());
        }
        col_sketch.setCellValueFactory(new PropertyValueFactory<>("jobSketch"));
        /*col_sketch.setCellFactory(param -> new TableCell<Jobs, byte[]>() {

            private ImageView imageView = new ImageView();

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(getImageFromBytes(item));
                    setGraphic(imageView);
                }
                this.setItem(item);
            }
        });*/
        col_type.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("jobCost"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        col_dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        col_dateComplete.setCellValueFactory(new PropertyValueFactory<>("dateComplete"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        AllJobsTable.setItems(jobList);
    }

    private Image getImageFromBytes(byte[] imgBytes) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imgBytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void changeToDash(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void changeToViewJobs(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllJobCreateEdit.fxml"));
        Parent root = loader.load();
        AllJobCreateEditController scene2Controller = loader.getController();
//need to put job id in method call
        scene2Controller.getPreviousVariables(1);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void printJob(ActionEvent actionEvent) {
    }

    @FXML
    private void completeJob(ActionEvent actionEvent) {
    }

    @FXML
    private void newJob(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backup_scenes/custSearchAndReturn.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
