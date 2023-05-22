package Game2;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.util.Random;
import TransitionScreens;

public class Board2 extends Stage {
    private Boolean foundWaldo = false;
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 800, 800);

    private static final Integer TIMELIMIT = 10;
    private int countdownSeconds = TIMELIMIT;
    private Text timerText = new Text("Timer: " + countdownSeconds);

    public Board2(int round_num, int number_people) {
        super();
        Random rd = new Random();
        this.setTitle("Game 2 - Board");
        Image people = new Image("file:./img/blue_button_small.png");

        this.setScene(scene);
        Pane board = new Pane();
        borderPane.setCenter(board);

        ImageView[] peopleView = new ImageView[number_people];
        for (int i = 0; i < number_people; i++){
            peopleView[i] = new ImageView(people);
            peopleView[i].setX(rd.nextInt(790));
            peopleView[i].setY(rd.nextInt(790));
            double scale = rd.nextDouble() + 0.5;
            peopleView[i].setScaleX(scale);
            peopleView[i].setScaleY(scale);

            board.getChildren().add(peopleView[i]);
        }
        Image waldo = new Image("file:./img/red_button_small.png");
        ImageView waldoView = new ImageView(waldo);
        waldoView.setX(rd.nextInt(700));
        waldoView.setY(rd.nextInt(700));
        double scale = rd.nextDouble() + 0.5;
        waldoView.setScaleX(scale);
        waldoView.setScaleY(scale);
        board.getChildren().add(waldoView);

        this.show();

        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(10, 10, 10, 10));
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        startCountdown();

        Image full_heart = new Image("file:./img/heart_icon.png");
        ImageView heartView = new ImageView(full_heart);
        Image empty_heart = new Image("file:./img/heart_icon_empty.png");
        heartView.setFitWidth(30);
        heartView.setFitHeight(30);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        statusBar.getChildren().addAll(timerText, spacer, heartView);

        Text roundNumText = new Text("ROUND " + round_num);
        roundNumText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        VBox topBar = new VBox(roundNumText, statusBar);
        topBar.setAlignment(Pos.TOP_CENTER);

        borderPane.setTop(topBar);

        waldoView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                foundWaldo = true;
                timerText.setText("Got me! Get ready for the next round...");
                Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), ae -> close()));
                tl.play();
                if (round_num == 1) {
                    Board2 round2 = new Board2(2, 600);
                }
                else if (round_num == 2) {
                    Board2 round3 = new Board2(3, 900);
                }
                else {
                    showWinScreen();
                }
            }
        });

    }

    public void startCountdown() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(countdownSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                    timerText.setText("Timer: " + countdownSeconds);
                })
        );
        timeline.setOnFinished(event -> {
            if (!foundWaldo) {
                close();
                TransitionScreens endScreen = new TransitionScreens();
                endScreen.showLossScreen();
            }
        });
        timeline.play();
    }


}