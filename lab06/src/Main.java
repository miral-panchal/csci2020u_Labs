import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Created by miral on 03/02/17.
 */
public class Main extends Application{

    @FXML
    private Canvas canvas;

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
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
        primaryStage.setTitle("Lab 06");
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
    }

    private void draw(GraphicsContext gc) {

        //Part 1
        for (int i=0; i < avgHousingPricesByYear.length; i++) {

            gc.setFill(Color.BLUE);
            double height = avgCommercialPricesByYear[i]/10000;
            gc.fillRect(30*(i+1),200-height,10,height);

            height = avgHousingPricesByYear[i]/10000;
            gc.setFill(Color.RED);
            int x = 10 + 30*(i+1);
            gc.fillRect(x,200-height,10,height);
        }

        gc.setStroke(Color.BLACK);
        gc.strokeLine(30,200,280,200);
        gc.strokeLine(30,50,30,200);

        //Part 2

        double startAngle = 0;
        double endAngle;
        double total = 0;
        for (int i = 0; i < pieColours.length; i++) {
            total += purchasesByAgeGroup[i];
        }

        for (int i = 0; i < pieColours.length; i++) {
            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);
            endAngle = (purchasesByAgeGroup[i]/total)*360;
            gc.fillArc(50, 300, 250, 250, startAngle, endAngle, ArcType.ROUND);
            gc.strokeArc(50,300,250,250,startAngle,endAngle,ArcType.ROUND);
            startAngle += endAngle;
        }
    }

    public static void main (String args[]) { launch(args); }
}
