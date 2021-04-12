package Dashboard;

import com.jfoenix.validation.RequiredFieldValidator;
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
        if(a.getText().isEmpty()){
            validTextFields = false;
            a.getStylesheets().add("assets/error.css");
            a.setPromptText("Cannot be empty.");
        } else {
            a.getStylesheets().add("assets/white.css");
            a.setPromptText(null);
        }
        if(b.getText().isEmpty()){
            validTextFields = false;
            b.getStylesheets().add("assets/error.css");
            b.setPromptText("Cannot be empty.");
        } else {
            b.getStylesheets().add("assets/white.css");
            b.setPromptText(null);
        }
        if(c.getText().isEmpty()){
            validTextFields = false;
            c.getStylesheets().add("assets/error.css");
            c.setPromptText("Cannot be empty.");
        }else {
            c.getStylesheets().add("assets/white.css");
            c.setPromptText(null);
        }
        if(d.getText().isEmpty()){
            validTextFields = false;
            d.getStylesheets().add("assets/error.css");
            d.setPromptText("Cannot be empty.");
        }else {
            d.getStylesheets().add("assets/white.css");
            d.setPromptText(null);
        }
        if(e.getText().isEmpty()){
            validTextFields = false;
            e.getStylesheets().add("assets/error.css");
            e.setPromptText("Cannot be empty.");
        }else {
            e.getStylesheets().add("assets/white.css");
            e.setPromptText(null);
        }
        if(f.getText().isEmpty()){
            validTextFields = false;
            f.getStylesheets().add("assets/error.css");
            f.setPromptText("Cannot be empty.");
        }else {
            f.getStylesheets().add("assets/white.css");
            f.setPromptText(null);
        }
        if(g.getText().isEmpty()){
            validTextFields = false;
            g.getStylesheets().add("assets/error.css");
            g.setPromptText("Cannot be empty.");
        }else {
            g.getStylesheets().add("assets/white.css");
            g.setPromptText(null);
        }
        if(h.getText().isEmpty()){
            validTextFields = false;
            h.getStylesheets().add("assets/error.css");
            h.setPromptText("Cannot be empty.");
        }else {
            h.getStylesheets().add("assets/white.css");
            h.setPromptText(null);
        }
        return validTextFields;
    }

    public static boolean costFieldNotEmpty(TextField c){
        boolean validTextField = true;
        if(c.getText().isEmpty()){
            validTextField = false;
            c.getStylesheets().add("assets/error.css");
            c.setPromptText("Cannot be empty.");
        }else{
            c.getStylesheets().add("assets/white.css");
            c.setPromptText(null);
        }
        return validTextField;
    }

    public static boolean costFormat (TextField c) {
        boolean validTextField = true;
        String pattern = "\\d{0,7}([\\\\.]\\d{0,2})?";
        if(!c.getText().matches(pattern))
        {
            validTextField = false;
            c.getStylesheets().add("assets/error.css");
            c.setPromptText("Enter monetary value with decimal.");
        }else{
            c.getStylesheets().add("assets/white.css");
            c.setPromptText(null);
        }
        return validTextField;
    }

    public static boolean phoneFormat(TextField i){
        boolean validTextField = true;
        String pattern = "(?:\\d{3}-){2}\\d{4}";
        if(!i.getText().matches(pattern) || i.getText().length() > 13){
            validTextField = false;
            i.getStylesheets().add("assets/error.css");
            i.setPromptText("Use correct format: XXX-XXX-XXXX");
        }else{
            i.getStylesheets().add("assets/white.css");
            i.setPromptText(null);
        }
        return validTextField;
    }

    public static boolean emailFormat(TextField i){
        boolean validTextField = true;
        if(!i.getText().matches("[a-zA-z0-9._-]+@[a-zA-Z0-9]+\\.com")){
            validTextField = false;
            i.getStylesheets().add("assets/error.css");
            i.setPromptText("Enter a valid email address.");
        }else{
            i.getStylesheets().add("assets/white.css");
            i.setPromptText(null);
        }
        return validTextField;
    }

    public static boolean zipFormat(TextField i){
        boolean validTextField = true;
        String pattern = "\\d{5}";
        if(!i.getText().matches(pattern)){
            validTextField = false;
            i.getStylesheets().add("assets/error.css");
            i.setPromptText("Enter five digit zip");
        }else{
            i.getStylesheets().add("assets/white.css");
            i.setPromptText(null);
        }
        return validTextField;
    }

    public static boolean comboBoxNotEmpty(ComboBox a, ComboBox b, ComboBox c){
        boolean validComboBox = true;
        if(a.getValue() == null){
            validComboBox = false;
            a.getStylesheets().add("assets/error.css");
            a.setPromptText("Cannot be empty.");
        }else{
            a.getStylesheets().add("assets/white.css");
            a.setPromptText(null);
        }
        if(b.getValue() == null){
            validComboBox = false;
            b.getStylesheets().add("assets/error.css");
            b.setPromptText("Cannot be empty.");
        }else{
            b.getStylesheets().add("assets/white.css");
            b.setPromptText(null);
        }
        if(c.getValue() == null){
            validComboBox = false;
            c.getStylesheets().add("assets/error.css");
            c.setPromptText("Cannot be empty.");
        }else{
            c.getStylesheets().add("assets/white.css");
            c.setPromptText(null);
        }
        return validComboBox;
    }

    public static boolean datePickerNotEmpty(DatePicker a, DatePicker b){
        boolean validDatePicker = true;
        if(a.getValue() == null){
            validDatePicker = false;
            a.getStylesheets().add("assets/error.css");
            a.setPromptText("Cannot be empty.");
        }else{
            a.getStylesheets().add("assets/white.css");
            a.setPromptText(null);
        }
        if(b.getValue() == null){
            validDatePicker = false;
            b.getStylesheets().add("assets/error.css");
            b.setPromptText("Cannot be empty.");
        }else{
            b.getStylesheets().add("assets/white.css");
            b.setPromptText(null);
        }
        return validDatePicker;
    }

    public static boolean validDate(DatePicker a, DatePicker b){
        boolean validDate = true;
        LocalDate ld = a.getValue();
        if(b.getValue().isBefore(ld)){
            validDate = false;
            b.getStylesheets().add("assets/error.css");
            b.setValue(null);
            b.setPromptText("Enter valid date.");
        }else{
            b.getStylesheets().add("assets/white.css");
            b.setPromptText(null);
        }
        return validDate;
    }
}
