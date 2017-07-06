package Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by miral on 23/03/17.
 */
public class Server_Main extends Application{


    static ServerSocket serverSocket;
    static Socket socket;
    static int MAX = 5;
    static BufferedReader in;

    static TextArea msg = new TextArea();

    BorderPane layout = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 10 - Server");

        GridPane grid = new GridPane();

        Button exit = new Button ("Exit");

        grid.setPadding(new Insets(15,15,15,15));

        grid.add(msg,0,0);
        grid.add(exit,0,1);

        grid.setVgap(15);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        Scene scene = new Scene(layout, 450, 300);
        layout.setCenter(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String args[]) {
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostName());


                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                msg.appendText(in.readLine());

                socket.close();
                launch(args);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
