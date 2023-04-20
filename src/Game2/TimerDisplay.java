package Game2;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class TimerDisplay extends Pane {
    private final int totalTime;
    private int remainingTime;
    private boolean running=true;
    private boolean firstRun=true;
    private long startTime;

    Rectangle2D rectangle = new Rectangle2D(0,0,800,100);

    public TimerDisplay(int totalTime) {
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
      //  this.getChildren().add(rectangle);
    }

    public void update(long time){
        if (firstRun){
            startTime=time;
            firstRun=false;
        }

    }
}
