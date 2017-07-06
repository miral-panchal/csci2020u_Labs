import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by miral on 17/03/17.
 */
public class Main extends Application{

    Canvas canvas;


    static String start = "2010";
    static String end = "2015";

    static String stock_1 = "GOOG";
    static String stock_2 = "AAPL";
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();

        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);

        canvas = new Canvas();

        canvas.setHeight(600);
        canvas.setWidth(800);

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 06");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        List <Float> GOOG_Price = download(stock_1);
        List <Float> AAPL_Price = download(stock_2);

        drawLinePlot(gc,GOOG_Price,AAPL_Price,stock_1,stock_2);

    }

    public List download(String symbol){

        List<Float> price = new ArrayList<>();
        try {
            URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s="+symbol+"&a=1&b=01&c="+start+"&d=11&e=31&f="+end+"&g=m");
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = in.readLine();
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");
                price.add(Float.parseFloat(data[4]));
            }
            in.close();
        }
        catch (Exception e) {System.err.println(e);}

        return price;
    }

    public void drawLinePlot(GraphicsContext gc,List<Float> stock1_Price,List<Float> stock2_Price,String stock_1, String stock_2){
        double padding = 50;
        double top = 0 + padding;
        double left = 0 + padding;
        double bottom = canvas.getHeight() - padding;
        double right = canvas.getWidth() - padding;



        gc.strokeRect(650,100,50,30);
        gc.strokeRect(650,50,50,30);

        gc.fillText(stock_1,710,120);
        gc.fillText(stock_2,710,70);


        gc.setFill(Color.BLUE);
        gc.fillRect(650,100,50,30);

        gc.setFill(Color.RED);
        gc.fillRect(650,50,50,30);

        gc.setStroke(Color.BLACK);
        gc.strokeLine(left, top, left, bottom);
        gc.strokeLine(left, bottom, right, bottom);

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < stock1_Price.size(); i++) {
            if (stock1_Price.get(i) < min) {
                min = stock1_Price.get(i);
            }
            if (stock1_Price.get(i) > max) {
                max = stock1_Price.get(i);
            }
        }
        for (int i = 0; i < stock2_Price.size(); i++) {
            if (stock2_Price.get(i) < min) {
                min = stock2_Price.get(i);
            }
            if (stock2_Price.get(i) > max) {
                max = stock2_Price.get(i);
            }
        }


        line(gc, stock1_Price, Color.BLUE, max);
        line(gc, stock2_Price, Color.RED, max);

    }

    public void line(GraphicsContext gc, List<Float> prices, Color colour, double max) {
        double padding = 50;
        double left = 0 + padding;
        double bottom = canvas.getHeight() - padding;




        // calculate the x and y spacing
        int count = prices.size();
        double xSpacing = (canvas.getWidth() - (2 * padding)) / count;
        double height = canvas.getHeight() - (2 * padding);

        gc.setStroke(colour);
        double lastX = left;
        double lastY = bottom - (height * prices.get(0) / max);

        for (int i = 1; i < prices.size(); i++) {
            double y = bottom - (height * prices.get(i) / max);
            gc.strokeLine(lastX, lastY, lastX + xSpacing, y);

            lastX += xSpacing;
            lastY = y;
        }
    }

    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);


        System.out.println("Stock 1: ");
        stock_1 = sc.nextLine();
        System.out.println("Stock 2: ");
        stock_2 = sc.nextLine();

        System.out.println("Start Year: ");
        start = sc.nextLine();
        System.out.println("End Year: ");
        end = sc.nextLine();

        launch(args);
    }
}
