package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by miral on 31/03/17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("SimpleBBS Server v1.0");
        primaryStage.setScene(new Scene(root, 400, 300));
        System.out.println("Showing window.");
        primaryStage.show();
        System.out.println("Window shown.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
