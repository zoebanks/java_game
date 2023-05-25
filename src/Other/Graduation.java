package Other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Graduation extends Stage {

    public Graduation() {
        Stage stage = new Stage();

        StackPane root = new StackPane();
        Text gradTitle = new Text("Congratulations!");
        gradTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text gradDescription = new Text("\n\nYou completed your study abroad experience");
        gradDescription.setTextAlignment(TextAlignment.CENTER);
        gradDescription.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        VBox text = new VBox(gradTitle, gradDescription);
        text.setAlignment(Pos.CENTER);
        root.getChildren().add(text);

        Scene enrollmentScene = new Scene(root, 800, 800);
        stage.setScene(enrollmentScene);
        stage.show();
    }
}
