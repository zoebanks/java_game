package Game2;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.*;


import java.util.Random;

public class Board3 extends Application {

    private static final int numberOfGuys=300;

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
        //borderPane.getChildren().add(board);
        borderPane.setCenter(board);

        ImageView[] peopleView = new ImageView[numberOfGuys];
        for (int i = 0; i < numberOfGuys; i++){
            peopleView[i] = new ImageView(people);
            peopleView[i].setX(rd.nextInt(700));
            peopleView[i].setY(rd.nextInt(700));
            double scale = rd.nextDouble() + 0.5;
            peopleView[i].setScaleX(scale);
            peopleView[i].setScaleY(scale);

            board.getChildren().add(peopleView[i]);
        }
        Image waldo = new Image("file:./img/red_button_small.png");
        ImageView waldoView = new ImageView(waldo);
        waldoView.setX(rd.nextInt(700));
        waldoView.setY(rd.nextInt(700));
        double scale = rd.nextDouble()+0.5;
        waldoView.setScaleX(scale);
        waldoView.setScaleY(scale);
        board.getChildren().add(waldoView);

        primaryStage.show();
        waldoView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Got me !");
            }
        });

        TimerDisplay timerDisplay = new TimerDisplay();
        timerDisplay.startCountdown();
        Text timerText = new Text("Timer: ");
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        borderPane.setTop(timerText);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                timerText.setText(timerDisplay.getTimer());

            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}