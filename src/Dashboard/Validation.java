package Dashboard;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;

public class Validation {

    public static boolean textFieldNotEmpty(TextField a, TextField b, TextField c, TextField d, TextField e, TextField f, TextField g, TextField h){
        boolean validTextFields = true;
        JOptionPane frame = new JOptionPane();
        if(a.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "First name cannot be empty.");
        }
        if(b.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "Last name cannot be empty.");
        }
        if(c.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "Phone cannot be empty.");
        }
        if(d.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "Email cannot be empty.");
        }
        if(e.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "Street address cannot be empty.");
        }
        if(f.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "State cannot be empty.");
        }
        if(g.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "City cannot be empty.");
        }
        if(h.getText().isEmpty()){
            validTextFields = false;
            JOptionPane.showMessageDialog(frame, "Zip cannot be empty.");
        }
        return validTextFields;
    }

    public static boolean costFieldNotEmpty(TextField c){
        boolean validTextField = true;
        JOptionPane frame = new JOptionPane();
        if(c.getText().isEmpty()){
            validTextField = false;
            JOptionPane.showMessageDialog(frame, "Cost cannot be empty.");
        }
        return validTextField;
    }

    public static boolean costFormat (TextField c) {
        boolean validTextField = true;
        JOptionPane frame = new JOptionPane();
        String pattern = "\\d{0,7}([\\\\.]\\d{0,2})?";
        if(!c.getText().matches(pattern))
        {
            validTextField = false;
            JOptionPane.showMessageDialog(frame, "Enter monetary value with decimal.");
        }
        return validTextField;
    }

    public static boolean phoneFormat(TextField i){
        boolean validTextField = true;
        JOptionPane frame = new JOptionPane();
        String pattern = "(?:\\d{3}-){2}\\d{4}";
        if(!i.getText().matches(pattern) || i.getText().length() > 13){
            validTextField = false;
            JOptionPane.showMessageDialog(frame, "Enter correct phone format: XXX-XXX-XXXX");
        }
        return validTextField;
    }

    public static boolean emailFormat(TextField i){
        boolean validTextField = true;
        JOptionPane frame = new JOptionPane();
        if(!i.getText().matches("[a-zA-z0-9._-]+@[a-zA-Z0-9]+\\.com")){
            validTextField = false;
            JOptionPane.showMessageDialog(frame, "Enter a valid email address.");
        }
        return validTextField;
    }

    public static boolean zipFormat(TextField i){
        boolean validTextField = true;
        JOptionPane frame = new JOptionPane();
        String pattern = "\\d{5}";
        if(!i.getText().matches(pattern)){
            validTextField = false;
            JOptionPane.showMessageDialog(frame, "Enter five digit zip.");
        }
        return validTextField;
    }

    public static boolean comboBoxNotEmpty(ComboBox a, ComboBox b, ComboBox c){
        boolean validComboBox = true;
        JOptionPane frame = new JOptionPane();
        if(a.getValue() == null){
            validComboBox = false;
            JOptionPane.showMessageDialog(frame, "Job type cannot be empty.");
        }
        if(b.getValue() == null){
            validComboBox = false;
            JOptionPane.showMessageDialog(frame, "Payment type cannot be empty.");
        }
        if(c.getValue() == null){
            validComboBox = false;
            JOptionPane.showMessageDialog(frame, "Job status cannot be empty.");
        }
        return validComboBox;
    }

    public static boolean datePickerNotEmpty(DatePicker a, DatePicker b){
        boolean validDatePicker = true;
        JOptionPane frame = new JOptionPane();
        if(a.getValue() == null){
            validDatePicker = false;
            JOptionPane.showMessageDialog(frame, "Start date cannot be empty.");
        }
        if(b.getValue() == null){
            validDatePicker = false;
            JOptionPane.showMessageDialog(frame, "Finish date cannot be empty.");
        }
        return validDatePicker;
    }

    public static boolean validDate(DatePicker a, DatePicker b){
        boolean validDate = true;
        JOptionPane frame = new JOptionPane();
        LocalDate ld = a.getValue();
        if(b.getValue().isBefore(ld)){
            validDate = false;
            JOptionPane.showMessageDialog(frame, "Finish date cannot be before start date.");
        }
        return validDate;
    }
}
