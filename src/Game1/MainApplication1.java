package Game1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.media.*;
import Other.TransitionScreens;

public class MainApplication1 extends Pane {

    private Stage instrStage = new Stage();
    private Boolean startButtonClicked = false;
    private Board1 board = new Board1(false);
    private boolean wonGame = false;

    public MainApplication1(Stage stage) throws Exception {

        StackPane root = new StackPane();

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/blue.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

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
        instrStage.setScene(instrScene);
        instrStage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                instrStage.close();
                board = new Board1(true);
            }
        });

        if (board.getWonGame()) {
            wonGame = true;
        }
    }

    public static void main(String[] args) {
 //       launch(args);
    }

    public boolean getWonGame() {
        return wonGame;
    }
}
