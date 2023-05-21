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

public class Board2 extends Stage {
    private Boolean foundWaldo = false;
    //int delaySecs = 3;
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 800, 800);

    private static final Integer TIMELIMIT = 10;
    private Timeline timeline;
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
        /*TimerDisplay timerDisplay = new TimerDisplay();
        timerDisplay.startCountdown();
        Text timerText = new Text("Timer: ");*/
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
                //TransitionScreen transitionScreen = new TransitionScreen();
                //displayTransitionScreen();
                /*PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> close());
                pause.play();*/
                foundWaldo = true;
                timerText.setText("Got me! Get ready for the next round...");
                Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), ae -> close()));
                tl.play();
                //close();
                //timerDisplay.foundCharacter();
                if (round_num == 1) {
                    Board2 round2 = new Board2(2, 600);
                    //if (round2.getStatusLostGame()) { lostGame2 = true; }
                }
                else if (round_num == 2) {
                    Board2 round3 = new Board2(3, 900);
                    //if (round3.getStatusLostGame()) { lostGame2 = true; }
                }
                else {
                    StackPane winnerPane = new StackPane();
                    Text winnerText = new Text("Congratulations, you beat all 3 rounds!" +
                            "\n\nGame 2 complete");
                    winnerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    winnerText.setTextAlignment(TextAlignment.CENTER);
                    winnerPane.getChildren().add(winnerText);
                    Stage winnerStage = new Stage();
                    Scene winnerScene = new Scene(winnerPane, 800, 800);
                    winnerStage.setScene(winnerScene);
                    winnerStage.show();
                }
            }
        });


        /*AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                timerText.setText(timerDisplay.getTimer());
                if(timerDisplay.roundLost()) {
                    heartView.setImage(empty_heart);
                    lostGame2 = true;
                }
            }
        };
        animationTimer.start();

        if(lostGame2) {
            showLossScreen();
        }*/

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
                showLossScreen();
            }
        });
        timeline.play();
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

        /*PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> lossScreenStage.close());
        pause.play();*/

        //lossScreenStage.close();
    }

    /*public void displayTransitionScreen() {
        StackPane root = new StackPane();
        Text text = new Text("Got me!\nGet ready for the next round...");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setTextAlignment(TextAlignment.CENTER);
        //StackPane.getChildren().add(text);

        Scene transitionScreenScene = new Scene(root, 800, 800);
        scene = transitionScreenScene;
        Stage transitionScreenStage = new Stage();
        transitionScreenStage.setScene(transitionScreenScene);
        transitionScreenStage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> close());
        pause.play();
    }*/

}