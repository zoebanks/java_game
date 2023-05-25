package Game2;

import javafx.animation.AnimationTimer;
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

public class MainApplication2 extends Pane {

    private Stage instructionStage = new Stage();
    private Stage board1Stage = new Stage();
    private Stage board2Stage = new Stage();
    private Stage board3Stage = new Stage();
    private Boolean startButtonClicked = false;

    private Boolean winResult = false;
    private Board2 round1;

    //@Override
    public MainApplication2(Stage stage) throws Exception {

        StackPane root = new StackPane();

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/blue.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Text instrTitle = new Text("Instructions");
        instrTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text instrDescription = new Text(
                "\n\nIt's time for the annual \"Le Beauf\" party!" +
                        "\nEveryone is dressed up in mustaches and sunglasses except for Jade." +
                        "\nFind Jade by correctly clicking on her character before the timer" +
                        "\nruns out. There will be 3 rounds total and 10 seconds for each" +
                        "\nround. Keep in mind that finding Jade will get more difficult as" +
                        "\nmore students get to the party. You will only get 1 life. If the " +
                        "\ntimer runs out during any of the rounds, you lose Game 2." +
                        "\nClick the \"Start\" button below to begin." +
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
        Stage instrStage = new Stage();
        instrStage.setScene(instrScene);
        instrStage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startButtonClicked = true;
                instrStage.close();
                round1 = new Board2(1, 100, true);
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (round1.getIsFinished()) {
                    System.out.println("Finished");
                }
            }
        };
    }
}
