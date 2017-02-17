import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * Created by miral on 14/01/17.
 */
public class Main extends Application {
    BorderPane layout = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 04");

        Label username_prompt = new Label("Username:");
        Label password_prompt = new Label("Password:");
        Label fullName_prompt = new Label("Full Name:");
        Label email_prompt = new Label ("E-Mail:");
        Label phone_number_prompt = new Label ("Phone #:");
        Label date_of_birth_prompt = new Label("Date of Birth");

        TextField username = new TextField();
        PasswordField password = new PasswordField();
        TextField fullName = new TextField();
        TextField email = new TextField ();
        TextField phone_number = new TextField();
        DatePicker date_of_birth = new DatePicker();

        Button register = new Button("Register");


        GridPane grid = new GridPane();

        grid.add(username_prompt,0,0);
        grid.add(password_prompt,0,1);
        grid.add(fullName_prompt,0,2);
        grid.add(email_prompt,0,3);
        grid.add(phone_number_prompt,0,4);
        grid.add(date_of_birth_prompt,0,5);

        grid.add(username,1,0);
        grid.add(password,1,1);
        grid.add(fullName,1,2);
        grid.add(email,1,3);
        grid.add(phone_number,1,4);
        grid.add(date_of_birth,1,5);
        grid.add(register,1,6);

        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(5);
        grid.setHgap(25);

        EmailValidator emailValidator = EmailValidator.getInstance();

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label validity = new Label();
                if (emailValidator.isValid(email.getText())) {
                    validity.setText("");
                    System.out.println("Username: "+username.getText());
                    System.out.println("Full Name: "+fullName.getText());
                    System.out.println("Email Address: "+email.getText());
                    System.out.println("Phone Number: "+phone_number.getText());
                    System.out.println("Date of Birth: "+date_of_birth.getValue());
                }
                else
                    validity.setText("Not a valid email address");

                grid.add(validity,2,3);
            }
        });

        Scene scene = new Scene(layout, 600, 300);

        layout.setCenter(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}