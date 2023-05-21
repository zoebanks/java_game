package Game1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private double max = 600 - SPRITE_SIZE;
    private double min = 0 + SPRITE_SIZE;

    private int num_lives_remaining = 3;
    private boolean allSpritesReachedTop = false;

    //@Override
    public Board1() {
        super();
        Stage primaryStage = new Stage();
        Pane root = new Pane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game 1 - La Passation");
        primaryStage.show();

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
                        }
                    }

                    //ToDo: change this because it is in AnimationTimer so it is updating
                    // consistently instead of one time

                    // Check if any sprite reaches the bottom
                    for (Sprite1 sprite : sprites) {
                        if (!sprite.isIntersected() && sprite.getY() >= HEIGHT) {
                            --num_lives_remaining;
                            System.out.println("-1 life");
                            if (num_lives_remaining < 1) {
                                gameRunning = false;
                                System.out.println("You lose!");
                            }
                            break;
                        }
                    }

                    //only need last sprite to cross with > 0 lives left
                    for (Sprite1 sprite : sprites) {
                        if (sprite.getId() == sprites.size() - 1 && sprite.getDirection() < 0 && sprite.getY() <= -240) {
                            //System.out.println("ID: " + sprite.getId() + ", Y: " + sprite.getY());
                            gameRunning = false;
                            allSpritesReachedTop = true;
                            System.out.println("You win!");
                        }
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

        for (int i = 0; i < 3; i++) {
            startX = SPRITE_SIZE / 2 + random_num_gen();
            Color spriteColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Sprite1 sprite = new Sprite1(startX, startY, SPRITE_SPEED, spriteColor, i + 1, 1);
            sprites.add(sprite);

            startY -= SPRITE_SIZE * staggeredDelay;

        }
    }

    public double random_num_gen() {
        return Math.floor(Math.random() *(max - min + 1) + min);

    }


}