package Other;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionScreens extends Pane {

    private Stage transitionStage = new Stage();
    private int gameNum;

    private int waldoX;
    private int waldoY;
    private double waldoScale;

    int countdownSeconds = 2;

    public TransitionScreens(int gameNum) {
        this.gameNum = gameNum;
    }

    public TransitionScreens(int gameNum, int waldoX, int waldoY, double waldoScale) {
        this.gameNum = gameNum;
        this.waldoX = waldoX;
        this.waldoY = waldoY;
        this.waldoScale = waldoScale;
    }
    public void showLossScreen() {
        StackPane pane = new StackPane();
        Pane waldoPane = new Pane();

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/red.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        waldoPane.setBackground(new Background(myBI));
        pane.setBackground(new Background(myBI));

        if (gameNum == 1) {
            HBox heartsBar = new HBox();
            heartsBar.setPadding(new Insets(10, 10, 10, 10));
            Image emptyHeart = new Image("file:./img/heart_icon_empty.png");
            ImageView[] emptyHearts = new ImageView[3];
            for (int i = 0; i < 3; i++) {
                emptyHearts[i] = new ImageView(emptyHeart);
                emptyHearts[i].setFitHeight(30);
                emptyHearts[i].setFitWidth(30);
                heartsBar.getChildren().add(emptyHearts[i]);
            }
            pane.getChildren().addAll(heartsBar);
            heartsBar.setAlignment(Pos.TOP_RIGHT);
        }
        else if (gameNum == 2) {
            Image waldo = new Image("file:./img/jade.png");
            ImageView waldoView = new ImageView(waldo);
            waldoView.setX(waldoX);
            waldoView.setY(waldoY + 87);
            waldoView.setScaleX(waldoScale);
            waldoView.setScaleY(waldoScale);
            waldoPane.getChildren().add(waldoView);
            pane.getChildren().add(waldoPane);
        }

        Text text = new Text("You lost :(");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(text);

        Scene lossScreenScene = new Scene(pane, 800, 800);
        transitionStage.setScene(lossScreenScene);
        transitionStage.show();

        delayFunction();
    }

    public void showWinScreen() {
        StackPane winnerPane = new StackPane();
        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/lightgreen.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        winnerPane.setBackground(new Background(myBI));
        Text winnerText = new Text();
        if (gameNum == 1) {
            winnerText = new Text("Congratulations, you served drinks the entire night!" +
                    "\n\nGame 1 complete");
        }
        else if (gameNum == 2) {
            winnerText = new Text("Congratulations, you beat all 3 rounds!" +
                    "\n\nGame 2 complete");
        }
        else {

        }
        winnerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        winnerText.setTextAlignment(TextAlignment.CENTER);
        winnerPane.getChildren().add(winnerText);
        Scene winnerScene = new Scene(winnerPane, 800, 800);
        transitionStage.setScene(winnerScene);
        transitionStage.show();
        delayFunction();

    }

    public void delayFunction() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(countdownSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                })
        );
        timeline.setOnFinished(event -> {
            transitionStage.close();
        });
        timeline.play();
    }

}
