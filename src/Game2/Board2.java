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

import java.io.File;
import java.io.IOException;
import java.util.Random;
import Other.TransitionScreens;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Board2 extends Stage {
    private Boolean foundWaldo = false;
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 800, 800);

    private static final Integer TIMELIMIT = 10;
    private int countdownSeconds = TIMELIMIT;
    private Text timerText = new Text("Timer: " + countdownSeconds);

    private int waldoX = 0;
    private int waldoY = 0;
    private double waldoScale = 0;

    private boolean isFinished = false;
    private boolean winResult = false;

    private boolean wonGame = false;
    private String audioFile = "./sounds/jeopardysong.wav";
    private AudioInputStream audioStream ;
    private Clip clip;

    int round_num = 1;

    public Board2(int round_num, int number_people, boolean isBoard1) {
        this.round_num = round_num;
        startGame(round_num, number_people);
    }

    public void startGame (int round_num, int number_people) {
        this.round_num = round_num;
        //super();
        Random rd = new Random();
        this.setTitle("Game 2 - Board");

        this.setScene(scene);
        Pane board = new Pane();
        borderPane.setCenter(board);

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/purple.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(myBI));

        ImageView[] peopleView = new ImageView[number_people];
        Image[] peopleImages = new Image[5];
        peopleImages[0] = new Image("file:./img/mustacheguy1.png");
        peopleImages[1] = new Image("file:./img/mustacheguy2.png");
        peopleImages[2] = new Image("file:./img/mustacheguy3.png");
        peopleImages[3] = new Image("file:./img/guywithglasses2.png");

        for (int i = 0; i < number_people; i++){
            Random randNum = new Random();
            int random_int = randNum.nextInt(4);
            peopleView[i] = new ImageView(peopleImages[random_int]);
            peopleView[i].setX(rd.nextInt(750));
            peopleView[i].setY(rd.nextInt(750));
            double scale = rd.nextDouble() + 0.25;
            peopleView[i].setScaleX(scale);
            peopleView[i].setScaleY(scale);

            board.getChildren().add(peopleView[i]);
        }
        waldoX = rd.nextInt(650);
        waldoY = rd.nextInt(650);
        waldoScale = rd.nextDouble() + 0.25;
        Image waldo = new Image("file:./img/jade.png");
        ImageView waldoView = new ImageView(waldo);
        waldoView.setX(waldoX);
        waldoView.setY(waldoY);
        waldoView.setScaleX(waldoScale);
        waldoView.setScaleY(waldoScale);
        board.getChildren().add(waldoView);

        this.show();
        //if (round_num == 1) {
            playMusic();
        //}

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
                stopMusic(audioStream, clip);
                timerText.setText("Got me! Get ready for the next round...");
                Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), ae -> close()));
                tl.play();
                if (round_num == 1) {
                    Board2 round2 = new Board2(2, 250, false);
                }
                else if (round_num == 2) {
                    Board2 round3 = new Board2(3, 500, false);
                }
                else {
                    TransitionScreens endScreen = new TransitionScreens(2);
                    endScreen.showWinScreen();
                    isFinished = true;
                    //stopMusic(audioStream, clip);
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
                stopMusic(audioStream, clip);
                close();
                TransitionScreens endScreen = new TransitionScreens(2, waldoX, waldoY, waldoScale);
                endScreen.showLossScreen();
                isFinished = true;
            }
        });
        timeline.play();
    }

    public void playMusic() {

        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFile));
            clip.open(audioStream);
            // Play the audio
            clip.start();
            // Close the resources
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic(AudioInputStream audioStream, Clip clip) {
        //if (round_num == 3) {
            clip.close();
        //}
        //audioStream.close();
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public boolean getWinResult() {
        return winResult;
    }


}