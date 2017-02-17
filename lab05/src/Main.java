import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;



/**
 * Created by miral on 14/01/17.
 */
public class Main extends Application {

    BorderPane layout = new BorderPane();
    TableView<StudentRecord> table = new TableView();
    GridPane grid = new GridPane();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 05");

        ObservableList data = DataSource.getAllMarks();
        table.setItems(data);

        TableColumn sid_column = new TableColumn("SID");
        TableColumn assignment_column = new TableColumn("Assignment");
        TableColumn midterm_column = new TableColumn("Midterm");
        TableColumn exam_column = new TableColumn("Final Exam");
        TableColumn mark_column = new TableColumn("Final Mark");
        TableColumn grade_column = new TableColumn("Letter Grade");

        sid_column.setMinWidth(120);
        assignment_column.setMinWidth(100);
        midterm_column.setMinWidth(100);
        exam_column.setMinWidth(100);
        mark_column.setMinWidth(100);
        grade_column.setMinWidth(100);

        table.getColumns().addAll(sid_column,assignment_column,midterm_column,exam_column,mark_column,grade_column);

        sid_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("ID"));
        assignment_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Assignment"));
        midterm_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Midterm"));
        exam_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Exam"));
        mark_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Mark"));
        grade_column.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Grade"));


        TextField id = new TextField();
        id.setPromptText("Student ID");
        TextField assignment = new TextField();
        assignment.setPromptText("Assignments/100");
        TextField midterm = new TextField();
        midterm.setPromptText("Midterm/100");
        TextField exam = new TextField();
        exam.setPromptText("Final Exam/100");

        Button add = new Button ("Add");
        add.setMinWidth(80);

        grid.add(id,7,0);
        grid.add(assignment,8,0);
        grid.add(midterm,7,1);
        grid.add(exam,8,1);
        grid.add(add,7,2);


        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(15);
        grid.setVgap(10);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                float assignment_mark = Float.parseFloat(assignment.getText());
                float midterm_mark = Float.parseFloat(midterm.getText());
                float exam_mark = Float.parseFloat(exam.getText());

                data.add(new StudentRecord(id.getText(),assignment_mark,midterm_mark,exam_mark));

                id.clear();
                assignment.clear();
                midterm.clear();
                exam.clear();
            }
        });

        layout.setCenter(table);
        layout.setBottom(grid);

        Scene scene = new Scene(layout, 620, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
