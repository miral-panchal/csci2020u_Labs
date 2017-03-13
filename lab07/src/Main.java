import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by miral on 03/03/17.
 */
public class Main extends Application{
    @FXML
    private Canvas canvas;
    private File weather_file = new File ("weatherwarnings-2015.csv");
    Map<String, Integer> warnings = new TreeMap<>();

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

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
    private void processFile() throws IOException {
        Scanner sc = new Scanner(weather_file);
        String line;
        while (sc.hasNextLine()){
            line = sc.nextLine();

            if (line.contains("FLASH FLOOD"))
                addToMap("FLASH FLOOD");

            else if (line.contains("SEVERE THUNDERSTORM"))
                addToMap("SEVER THUNDERSTORM");

            else if (line.contains("SPECIAL MARINE"))
                addToMap("SPECIAL MARINE");

            else if (line.contains("TORNADO"))
                addToMap("TORNADO");

        }
        System.out.println(warnings);
    }

    private void addToMap(String weather){
        if (warnings.containsKey(weather)) {
            int old_count = warnings.get(weather);
            warnings.put(weather,old_count+1);
        }
        else
            warnings.put(weather,1);
    }

    private void draw(GraphicsContext gc) {
        double startAngle = 0;
        double endAngle;
        double total = 0;

        Set<String> keys = warnings.keySet();
        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            total += warnings.get(key);
        }

        int i = 0;

        keys = warnings.keySet();
        keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);

            endAngle =(warnings.get(key)/total)*360;
            gc.fillArc(350,150,300,300,startAngle,endAngle,ArcType.ROUND);
            gc.strokeArc(350,150,300,300,startAngle,endAngle,ArcType.ROUND);
            startAngle+=endAngle;

            int y = 50;
            gc.fillRect(75,200+(i*y),50,30);
            gc.strokeRect(75,200+(i*y),50,30);

            gc.strokeText(key,140,220+(i*y));

            i++;
        }

    }


    public static void main (String args[]) { launch(args); }
}
