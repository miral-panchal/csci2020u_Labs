package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by miral on 23/03/17.
 */
public class Client_Main extends Application{

    static Socket socket;
    static PrintWriter out;

    BorderPane layout = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 10 - Client");

        GridPane grid = new GridPane();

        Label u_name = new Label("Username: ");
        Label msg = new Label("Message: ");

        TextField u_name_field = new TextField();
        TextField msg_field = new TextField();


        Button send = new Button("Send");
        Button exit = new Button ("Exit");

        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(15);

        grid.add(u_name,0,0);
        grid.add(msg,0,1);
        grid.add(u_name_field,1,0);
        grid.add(msg_field,1,1);
        grid.add(send,0,2);
        grid.add(exit,0,3);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    socket.close();
                    out.close();
                }
                catch (Exception e){e.printStackTrace();}
                primaryStage.close();
            }
        });
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = "";
                if(u_name_field.getText()!=null) {
                    message = u_name_field.getText() + ": " + msg_field.getText();
                    u_name_field.clear();
                    msg_field.clear();
                }
                else
                    System.out.println("Username Required!");
                out.println(message);
                out.flush();
            }
        });

        Scene scene = new Scene(layout, 300, 200);
        layout.setCenter(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String args[]){
        try {
            socket = new Socket("localhost", 8080);
            out = new PrintWriter(socket.getOutputStream());


            launch(args);


        }
        catch (Exception e) {e.printStackTrace();}
    }
}
