package Game2;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerDisplay extends Pane {

    private int countdownSeconds = 10;
    private Boolean isFinished = false;
    private Boolean isWinner = false;
    public void startCountdown() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(countdownSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                })
        );
        timeline.setOnFinished(event -> {
            isFinished = true;
        });
        timeline.play();
    }

    public String getTimer() {
        if (isWinner) {
            return "Got me! You win.";
        }
        else if (isFinished) {
            return "Timer finished. You lose.";

        }
        else { return "Timer: " + countdownSeconds; }
    }

    public void foundCharacter() {
        isWinner = true;
        isFinished = true;
    }

    public Boolean getIsWinner() {
        return isWinner;
    }
    public Boolean roundLost() {
        return isFinished && !isWinner;
    }

}

