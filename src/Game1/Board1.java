package Game1;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
//import javafx.scene.media.*;
//import javafx.scene.media.MediaPlayer;
//import java.applet.AudioClip;
//import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Other.TransitionScreens;

import javax.print.attribute.standard.Media;

public class Board1 extends Stage {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final double SPRITE_SIZE = 50;
    private static final double SPRITE_HEIGHT = 80;
    private static final double SPRITE_SPEED = 3;

    private static final double PLAYER_SIZE = 50;
    private static final double PLAYER_HEIGHT = 80;
    private static final double PLAYER_SPEED = 25;

    private double playerX = WIDTH / 2 - PLAYER_SIZE / 2;
    private double playerY = HEIGHT - (4 * PLAYER_SIZE);

    private List<Sprite1> sprites;
    //private boolean playerIntersected = false;
    private boolean gameRunning = true;

    private Random random = new Random();

    private Canvas canvas = new Canvas(WIDTH, HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    private double max = 800 - SPRITE_SIZE;
    private double min = 0 + SPRITE_SIZE;

    private int numLivesRemaining = 3;
    private int NUM_SPRITES = 17;
    private int countdownSeconds = 30;
    private Text timerText = new Text("Timer: 30");

    Stage primaryStage = new Stage();
    private HBox statusBar = new HBox();
    private HBox heartsBar = new HBox();
    private Image fullHeart = new Image("file:./img/heart_icon.png");
    private Image emptyHeart = new Image("file:./img/heart_icon_empty.png");
    private ImageView[] heartsFull = new ImageView[3];
    private ImageView[] heartsEmpty = new ImageView[3];

    Timeline timeline = new Timeline();

    private Image playerImage;
    private StackPane root = new StackPane();
    private Scene scene = new Scene(root, WIDTH, HEIGHT);

    public Board1() {
        //super();
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game 1 - La Passation");
        primaryStage.show();

        statusBar.setPrefWidth(WIDTH);
        statusBar.setPadding(new Insets(10, 10, 10, 10));
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        VBox characterOptions = new VBox();
        Text characterText = new Text("Choose your character: ");
        characterText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        HBox characterButtons = new HBox();
        characterButtons.setPadding(new Insets(20, 20, 20, 20));
        Button michigan = new Button("Michigan Guy");
        Button illinois = new Button("Illinois Guy");
        Button zoe = new Button("Zoe");
        Button pitt = new Button("Pitt Guy");
        characterButtons.setSpacing(10);
        characterButtons.getChildren().addAll(michigan, illinois, pitt, zoe);
        characterButtons.setAlignment(Pos.CENTER);
        characterOptions.getChildren().addAll(characterText, characterButtons);
        characterOptions.setAlignment(Pos.CENTER);
        root.getChildren().add(characterOptions);

        michigan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                characterOptions.getChildren().clear();
                playerImage = new Image("file:./img/michiganguy.png");
                startGame();
            }
        });
        illinois.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                characterOptions.getChildren().clear();
                playerImage = new Image("file:./img/illinoisguy.png");
                startGame();
            }
        });
        zoe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                characterOptions.getChildren().clear();
                playerImage = new Image("file:./img/zoe.png");
                startGame();
            }
        });
        pitt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                characterOptions.getChildren().clear();
                playerImage = new Image("file:./img/pittguy.png");
                startGame();
            }
        });
    }

    public void startGame() {

        startCountdown();
        //playMusic();

        for (int i = 0; i < 3; i++) {
            heartsFull[i] = new ImageView(fullHeart);
            heartsFull[i].setFitWidth(30);
            heartsFull[i].setFitHeight(30);
            heartsEmpty[i] = new ImageView(emptyHeart);
            heartsEmpty[i].setFitWidth(30);
            heartsEmpty[i].setFitHeight(30);
            heartsBar.getChildren().add(heartsFull[i]);
        }

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        statusBar.getChildren().addAll(timerText, spacer, heartsBar);
        root.getChildren().add(statusBar);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                playerX -= PLAYER_SPEED;
            } else if (e.getCode() == KeyCode.RIGHT) {
                playerX += PLAYER_SPEED;
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                // Stop player movement when the keys are released
                playerX += 0;
            }
        });

        sprites = new ArrayList<>();
        createSprites();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Clear the canvas
                gc.clearRect(0, 0, WIDTH, HEIGHT);

                if (gameRunning) {
                    for (Sprite1 sprite : sprites) {
                        sprite.setY(sprite.getY() + (sprite.getSpeed() * sprite.getDirection()));
                        sprite.draw(gc);

                        Rectangle2D spriteRect = new Rectangle2D(sprite.getX(), sprite.getY(), SPRITE_SIZE, SPRITE_HEIGHT);
                        Rectangle2D playerRect = new Rectangle2D(playerX, playerY, PLAYER_SIZE, SPRITE_HEIGHT);
                        if (playerRect.intersects(spriteRect)) {
                            sprite.setDirection(-1);
                            sprite.setIntersected(true);
                            sprite.setId(sprite.getId() + 4);
                        }
                        else if (sprite.getY() > playerY - 5) {
                            sprite.setDirection(-1);
                            sprite.setIntersected(false);
                            sprite.setId(sprite.getId() + 2);
                        }
                    }

                    for (Sprite1 sprite : sprites) {
                        if (sprite.getY() < 0 && !sprite.isIntersected() && sprite.getDirection() < 0) {
                            minusOne(sprite);
                        }
                    }

                    if (numLivesRemaining < 1) {
                        gameRunning = false;
                        primaryStage.close();
                        TransitionScreens endScreen = new TransitionScreens(1);
                        endScreen.showLossScreen();
                        timeline.stop();
                    }

                    // Check boundaries for player sprite
                    if (playerX < 0) {
                        playerX = 0;
                    } else if (playerX > WIDTH - PLAYER_SIZE) {
                        playerX = WIDTH - PLAYER_SIZE;
                    }

                    gc.drawImage(playerImage, playerX, playerY, PLAYER_SIZE, PLAYER_HEIGHT);

                }
            }
        }.start();
    }

    private void createSprites() {
        sprites.clear();

        double startY = -SPRITE_SIZE;
        double staggeredDelay = 5; // Delay between each sprite creation
        double startX = 0;

        for (int i = 0; i < NUM_SPRITES; i++) {
            startX = SPRITE_SIZE / 2 + random_num_gen();
            Sprite1 sprite = new Sprite1(startX, startY, SPRITE_SPEED, i, 1);
            sprites.add(sprite);
            startY -= SPRITE_SIZE * staggeredDelay;
        }
    }

    public double random_num_gen() {
        return Math.floor(Math.random() *(max - min + 1) + min);
    }

    public void minusOne(Sprite1 sprite) {
        if (!sprite.getLostLife()) {
            sprite.setLostLife(true);
            --numLivesRemaining;
            changeHeartView();
        }
    }

    public void changeHeartView() {
        heartsBar.getChildren().clear();
        if (numLivesRemaining == 2) {
            heartsBar.getChildren().addAll(heartsEmpty[0], heartsFull[1], heartsFull[2]);
        }
        else if (numLivesRemaining == 1) {
            heartsBar.getChildren().addAll(heartsEmpty[0], heartsEmpty[1], heartsFull[2]);
        }
        else {
            heartsBar.getChildren().addAll(heartsEmpty[0], heartsEmpty[1], heartsEmpty[2]);
        }
    }

    public void startCountdown() {
        timeline.setCycleCount(countdownSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdownSeconds--;
                    timerText.setText("Timer: " + countdownSeconds);
                })
        );
        timeline.setOnFinished(event -> {
            primaryStage.close();
            TransitionScreens endScreen = new TransitionScreens(1);
            endScreen.showWinScreen();
        });
        timeline.play();
    }

    public void playMusic() {
        /*String musicFile = "file:./sounds/fadeup.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();*/

        /*Media sound = new Media(getClass().getResource("file:./sounds/fadeup.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();*/

        //AudioClip music = new AudioClip(this.getClass().getResource("file:./sounds/fadeup.mp3").toExternalForm());

    }

}
