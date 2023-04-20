package Game2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.text.View;
import java.security.Key;

public class Board2 extends Application {

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int NUM_SEC = 30;
    private int sec_left = NUM_SEC;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game 2 - Board");
        /*Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.show();*/

        BorderPane borderPane = new BorderPane();
        Scene boardScene = new Scene(borderPane, 800, 800);

        GridPane board = new GridPane();
        //board.setPrefSize(256,256);
        board.setMaxSize(512, 512);
        //board.setStyle("-fx-background-color: #C0C0C0;");
        borderPane.setCenter(board);
        primaryStage.setScene(boardScene);
        primaryStage.show();

        Button[][] buttons = new Button[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Button blueButton = new Button();
                Image blueButtonImg = new Image("file:./img/blue_button.png");
                ImageView blueButtonView = new ImageView(blueButtonImg);
                blueButtonView.setFitWidth(board.getWidth() / COLS);
                blueButtonView.setFitHeight(board.getHeight() / ROWS);
                blueButtonView.setPreserveRatio(true);
                blueButton.setGraphic(blueButtonView);
                buttons[row][col] = blueButton;
                board.add(blueButton, col, row);

                blueButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Incorrect");
                    }
                });
            }
        }

        Button redButton = new Button();
        Image redButtonImg = new Image("file:./img/red_button.png");
        ImageView redButtonView = new ImageView(redButtonImg);
        //redButton.setPrefSize(buttonWidth, buttonHeight);
        redButtonView.setFitHeight(board.getHeight() / ROWS);
        redButtonView.setFitWidth(board.getWidth() / COLS);
        redButtonView.setPreserveRatio(true);
        redButton.setGraphic(redButtonView);
        int redRow = (int) (Math.random() * ROWS);
        int redCol = (int) (Math.random() * COLS);
        buttons[redRow][redCol] = redButton;
        board.add(redButton, redCol, redRow);

        redButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Correct");
            }
        });


        Button timerButton = new Button();
        timerButton.setText("Timer: ");
        borderPane.setTop(timerButton);

        /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                public void handle(ActionEvent event) {
                    sec_left--;
                    timerButton.setText("Time remaining: " + sec_left + " seconds");
                    if (sec_left <= 0) {
                        timeline.stop();
                        timerButton.setText("Time's up!");
                    }
                }
            }
        }));
        timeline.setCycleCount(NUM_SEC);
        timeline.play(); */

        /*board.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Correct"
                        + mouseEvent.getSceneX() + "," + mouseEvent.getSceneY());
                primaryStage.setScene(nextScene);
            }
        });*/

    }
    public static void main(String[] args) {
        launch(args);
    }
}

