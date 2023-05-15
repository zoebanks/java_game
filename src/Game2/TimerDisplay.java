package Game2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class TimerDisplay extends Pane {

    private int countdownSeconds = 10;
    public void startCountdown() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(countdownSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                })
        );
        timeline.setOnFinished(event -> {
            System.out.println("Countdown Complete!");
        });
        timeline.play();
    }

    public String getTimer() {
        return "Timer: " + countdownSeconds;
    }
}
 /*private final int totalTime = 10;
    private int remainingTime;
    private boolean running = true;
    private boolean firstRun = true;
    private long startTime;
    Rectangle2D rectangle = new Rectangle2D(0,0,800,100);

    public TimerDisplay(int totalTime) {
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
        //this.getChildren().add(rectangle);
    }

    public void update(long time){
        if (firstRun){
            startTime = time;
            firstRun = false;
        }

    }
 */

