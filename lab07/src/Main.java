import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by miral on 03/03/17.
 */
public class Main extends Application{
    @FXML
    private Canvas canvas;
    private File weather_file = new File ("weatherwarnings-2015.csv");
    Map<String, Integer> warnings = new TreeMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);

        canvas = new Canvas();
        canvas.setHeight(600);
        canvas.setWidth(800);

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        processFile();
        draw(gc);
    }

    private void processFile() {
        try {
            Scanner sc = new Scanner(weather_file);

            while (sc.hasNext()) {
                String temp = sc.next();
                if(temp.equalsIgnoreCase("FLASH FLOOD")) {
                    int old_count = warnings.get("flash flood");
                    warnings.put("flash flood",old_count+1);
                }

                else if(temp.equalsIgnoreCase("SEVERE THUNDERSTORM")) {
                    int old_count = warnings.get("severe thunderstorm");
                    warnings.put("severe thunderstorm",old_count+1);
                }

                else if(temp.equalsIgnoreCase("SPECIAL MARINE")) {
                    int old_count = warnings.get("special marine");
                    warnings.put("special marine",old_count+1);
                }

                else if(temp.equalsIgnoreCase("TORNADO")) {
                    int old_count = warnings.get("tornado");
                    warnings.put("tornado",old_count+1);
                }
            }

            System.out.println(warnings);
        }
        catch (Exception e) {e.printStackTrace();}
    }

    private void draw(GraphicsContext gc) {
    }

    public static void main (String args[]) { launch(args); }
}
