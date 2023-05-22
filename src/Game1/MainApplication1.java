package Game1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import Other.TransitionScreens;

public class MainApplication1 extends Application {

    private Stage instrStage = new Stage();
    private Boolean startButtonClicked = false;

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();
        Text instrTitle = new Text("Instructions");
        instrTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text instrDescription = new Text(
                "\n\nIt's time for the annual \"La Passation\" party!" +
                        "\nServe incoming students drinks at the bar. Move your character" +
                        "\nright and left using the arrow keys. You have 30 seconds to prove" +
                        "\nyour worth as a bartender. Deliver drinks by running into students." +
                        "\nYou have 3 lives total. If a student leaves without a drink, you" +
                        "\nlose one life." +
                        "\n\nGood luck!\n\n");
        instrDescription.setTextAlignment(TextAlignment.CENTER);
        instrDescription.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        instrTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        VBox text = new VBox(instrTitle, instrDescription, startButton);
        text.setAlignment(Pos.CENTER);
        root.getChildren().add(text);

        Scene instrScene = new Scene(root, 800, 800);
        //Stage instrStage = new Stage();
        instrStage.setScene(instrScene);
        instrStage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                instrStage.close();
                Board1 board = new Board1();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
