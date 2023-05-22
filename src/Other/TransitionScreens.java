package Other;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class TransitionScreens extends Pane {
    private int gameNum;
    private int waldoX;
    private int waldoY;
    private double waldoScale;

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

        if (gameNum == 2) {
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
        Stage lossScreenStage = new Stage();
        lossScreenStage.setScene(lossScreenScene);
        lossScreenStage.show();
    }

    public void showWinScreen() {
        StackPane winnerPane = new StackPane();
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
        Stage winnerStage = new Stage();
        Scene winnerScene = new Scene(winnerPane, 800, 800);
        winnerStage.setScene(winnerScene);
        winnerStage.show();
    }

}
