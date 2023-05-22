package Game1;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Other.TransitionScreens;

public class Board1 extends Stage {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final double SPRITE_SIZE = 40;
    private static final double SPRITE_SPEED = 2;

    private static final double PLAYER_SIZE = 40;
    private static final double PLAYER_SPEED = 25;

    private double playerX = WIDTH / 2 - PLAYER_SIZE / 2;
    private double playerY = HEIGHT - (4 * PLAYER_SIZE);

    private List<Sprite1> sprites;
    private boolean playerIntersected = false;
    private boolean gameRunning = true;

    private Random random = new Random();

    private Canvas canvas = new Canvas(WIDTH, HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    private double max = 800 - SPRITE_SIZE;
    private double min = 0 + SPRITE_SIZE;

    private int numLivesRemaining = 3;
    private int NUM_SPRITES = 13;
    private int countdownSeconds = 31;
    private Text timerText = new Text("Timer: ");

    Stage primaryStage = new Stage();
    private HBox statusBar = new HBox();
    private HBox heartsBar = new HBox();
    private Image fullHeart = new Image("file:./img/heart_icon.png");
    private Image emptyHeart = new Image("file:./img/heart_icon_empty.png");
    private ImageView heartView1 = new ImageView(fullHeart);
    private ImageView heartView2 = new ImageView(fullHeart);
    private ImageView heartView3 = new ImageView(fullHeart);
    private ImageView emptyHeartView1 = new ImageView(emptyHeart);
    private ImageView emptyHeartView2 = new ImageView(emptyHeart);
    private ImageView emptyHeartView3 = new ImageView(emptyHeart);

    public Board1() {
        //super();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game 1 - La Passation");
        primaryStage.show();

        statusBar.setPrefWidth(WIDTH);
        statusBar.setPadding(new Insets(10, 10, 10, 10));
        timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        startCountdown();

        heartView1.setFitWidth(30);
        heartView1.setFitHeight(30);
        heartView2.setFitWidth(30);
        heartView2.setFitHeight(30);
        heartView3.setFitWidth(30);
        heartView3.setFitHeight(30);
        emptyHeartView1.setFitWidth(30);
        emptyHeartView1.setFitHeight(30);
        emptyHeartView2.setFitWidth(30);
        emptyHeartView2.setFitHeight(30);
        emptyHeartView3.setFitWidth(30);
        emptyHeartView3.setFitHeight(30);

        heartsBar.getChildren().addAll(heartView1, heartView2, heartView3);
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
                    // Update sprite positions
                    for (Sprite1 sprite : sprites) {
                        sprite.setY(sprite.getY() + (sprite.getSpeed() * sprite.getDirection()));
                    }

                    // Check for player-sprite intersection
                    for (Sprite1 sprite : sprites) {
                        Rectangle2D spriteRect = new Rectangle2D(sprite.getX(), sprite.getY(), SPRITE_SIZE, SPRITE_SIZE);
                        Rectangle2D playerRect = new Rectangle2D(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
                        if (playerRect.intersects(spriteRect)) {
                            sprite.setDirection(-1);
                            sprite.setIntersected(true);
                            sprite.setColor(Color.GREEN);
                        }
                        else if (sprite.getY() > playerY) {
                            sprite.setDirection(-1);
                            sprite.setColor(Color.RED);
                        }
                    }

                    for (Sprite1 sprite : sprites) {
                        if (sprite.getY() < SPRITE_SIZE && !sprite.isIntersected() && sprite.getDirection() < 0) {
                            minusOne(sprite);
                        }
                    }


                    if (numLivesRemaining < 1) {
                        gameRunning = false;
                        primaryStage.close();
                        TransitionScreens endScreen = new TransitionScreens(1);
                        endScreen.showLossScreen();
                    }

                    // Draw the sprites
                    for (Sprite1 sprite : sprites) {
                        sprite.draw(gc);
                    }

                    // Check boundaries for player sprite
                    if (playerX < 0) {
                        playerX = 0;
                    } else if (playerX > WIDTH - PLAYER_SIZE) {
                        playerX = WIDTH - PLAYER_SIZE;
                    }

                    gc.setFill(Color.BLUE);
                    gc.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
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
            Color spriteColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            if (spriteColor == Color.RED || spriteColor == Color.GREEN) {
                spriteColor = Color.GRAY;
            }
            Sprite1 sprite = new Sprite1(startX, startY, SPRITE_SPEED, spriteColor, i, 1);
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
            System.out.println("-1 Life. Lives Remaining: " + numLivesRemaining);
            changeHeartView();
        }
    }

    public void changeHeartView() {
        heartsBar.getChildren().clear();
        if (numLivesRemaining == 2) {
            heartsBar.getChildren().addAll(emptyHeartView1, heartView2, heartView3);
        }
        else if (numLivesRemaining == 1) {
            heartsBar.getChildren().addAll(emptyHeartView1, emptyHeartView2, heartView3);
        }
        else {
            heartsBar.getChildren().addAll(emptyHeartView1, emptyHeartView2, emptyHeartView3);
        }
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
            primaryStage.close();
            TransitionScreens endScreen = new TransitionScreens(1);
            endScreen.showWinScreen();
        });
        timeline.play();
    }


}
