package Other;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class TransitionScreens extends Pane {
    private int gameNum;

    public TransitionScreens(int gameNum) {
        this.gameNum = gameNum;
    }

    public void showLossScreen() {
        StackPane pane = new StackPane();
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
