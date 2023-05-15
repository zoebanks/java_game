package Game2;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

import java.util.Random;

public class Board3 extends Application {
    //private static final int numberOfGuys=300;
    private int round_num = 1;
    private int number_people = 300;
    private Boolean lostGame2 = false;

    /*public Board3 (int round, int people) {
        this.round_num = round;
        this.number_people = people;
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {

        Random rd = new Random();
        primaryStage.setTitle("Game 2 - Board");
        //Group root = new Group();
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 800);
        Image people = new Image("file:./img/blue_button_small.png");

        primaryStage.setScene(scene);
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

        primaryStage.show();

        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(10, 10, 10, 10));
        TimerDisplay timerDisplay = new TimerDisplay();
        timerDisplay.startCountdown();

        Text timerText = new Text("Timer: ");
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

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
                timerDisplay.foundCharacter();
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
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
    }

    public Boolean getStatus() {
        return lostGame2;
    }

    public static void main(String[] args) { launch(args); }
}