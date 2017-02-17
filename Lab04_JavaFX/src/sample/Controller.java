package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;


public class Controller {
    @FXML private TextField username_field;
    @FXML private TextField name_field;
    @FXML private TextField email_field;
    @FXML private TextField phone_number_field;
    @FXML private DatePicker dob_field;
    @FXML private Label validity;


    EmailValidator emailValidator = EmailValidator.getInstance();
    public void register(ActionEvent event){

        if (emailValidator.isValid(email_field.getText())) {
            validity.setText("");

            System.out.println("Username: " + username_field.getText());
            System.out.println("Full Name: " + name_field.getText());
            System.out.println("Email Address: " + email_field.getText());
            System.out.println("Phone Number: " + phone_number_field.getText());
            System.out.println("Date of Birth: " + dob_field.getValue());
        }
        else
            validity.setText("Email is not valid");
    }
}


