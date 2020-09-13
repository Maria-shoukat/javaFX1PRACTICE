package VP1;

import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    private TextField MarksBox;
    private DatePicker DatePicker;


    public Main() {
        MarksBox = new TextField();
        MarksBox.setPrefWidth(200);
        DatePicker = new DatePicker();
        DatePicker.setPrefWidth(200);
    }

    @Override
    public void start(Stage primaryStage) {

        Label TitleLabel = new Label("My CP Tracker");

        TitleLabel.setFont(new Font("Roboto", 25));

        Label DateLabel = new Label("Date:");
        DateLabel.setFont(new Font(20));
        DateLabel.setStyle("-fx-base: Lightgray;");
        Label MarksLabel = new Label("Marks:");
        MarksLabel.setFont(new Font(20));
        MarksLabel.setStyle("-fx-base: Lightgray;");


        Button saveBtn = new Button("Save Data");
        saveBtn.setStyle("-fx-base: ghostwhite;");
        saveBtn.setFont(new Font(20));
        saveBtn.setOnAction((ActionEvent event) -> {
            save();
        });

        HBox dateBox = new HBox(70);
        dateBox.getChildren().add(DateLabel);
        dateBox.getChildren().add(DatePicker);
        dateBox.setAlignment(Pos.CENTER);


        HBox marksBox = new HBox(70);
        marksBox.getChildren().add(MarksLabel);
        marksBox.getChildren().add(MarksBox);
        marksBox.setAlignment(Pos.CENTER);

        HBox SavebtnBox = new HBox();
        SavebtnBox.getChildren().add(saveBtn);
        SavebtnBox.setAlignment(Pos.CENTER_RIGHT);
        SavebtnBox.setPadding(new Insets(0, 20, 0, 0));


        VBox vbox = new VBox(20);
        vbox.getChildren().add(TitleLabel);
        vbox.getChildren().add(marksBox);
        vbox.getChildren().add(dateBox);
        vbox.getChildren().add(SavebtnBox);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(vbox);


        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("181380006 CP Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("cp.txt", true))) {
            String Data = "------------- CP Marks on " + DatePicker.getValue().toString() + " ------------\n";
            Data += " Marks: " + MarksBox.getText();
            writer.println(Data);
            alert(Data);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void alert(String data) {
        Alert alert= new Alert(AlertType.INFORMATION);
        alert.setTitle("CP Data Saved");
        alert .setHeaderText("Your CP data is saved Successfully");
        alert .setContentText(data);
        alert.showAndWait();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}