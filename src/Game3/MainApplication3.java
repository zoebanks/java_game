package Game3;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApplication3 extends Application {

    private static final int TILE_SIZE = 40;
    private static final int WIDTH = 12;
    private static final int HEIGHT = 12;

    private static final String[] MAP = {
            "############",
            "#......#...#",
            "#.####.#.#.#",
            "#.#....#.#.#",
            "#.#.##.#.#.#",
            "#.#.##.#.#.#",
            "#.#....#.#.#",
            "#.########.#",
            "#..........#",
            "############"
    };

    private int playerX;
    private int playerY;

    private Pane root = new Pane();
    private Pane tiles = new Pane();
    private Pane dots = new Pane();

    private ImageView pacman;
    private int pacmanDirectionX;
    private int pacmanDirectionY;

    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        //scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pacman Game");
        primaryStage.show();

        createMap();
        //createPlayer();
        //createDots();

        /*timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
                checkCollisions();
            }
        };
        timer.start();*/
    }

    private void createMap() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                char tile = MAP[y].charAt(x);
                Rectangle rect = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (tile == '#') {
                    rect.setFill(Color.BLUE);
                    //tiles.getChildren().add(rect);
                } else if (tile == '.') {
                    rect.setFill(Color.BLACK);
                    //dots.getChildren().add(rect);
                }
                root.getChildren().add(rect);
            }
        }
        //root.getChildren().addAll(tiles, dots);
    }

   /* private void createPlayer() {
        playerX = 1;
        playerY = 1;

        Image pacmanImage = new Image("file:./img/blue_button_small.png");
        pacman = new ImageView(pacmanImage);
        pacman.setFitWidth(TILE_SIZE);
        pacman.setFitHeight(TILE_SIZE);
        pacman.setX(playerX * TILE_SIZE);
        pacman.setY(playerY * TILE_SIZE);

        root.getChildren().add(pacman);
    }

    private void createDots() {
        for (Node dot : dots.getChildren()) {
            Rectangle rect = (Rectangle) dot;
            int x = (int) rect.getX() / TILE_SIZE;
            int y = (int) rect.getY() / TILE_SIZE;

            if (x == playerX && y == playerY) {
                dots.getChildren().remove(dot);
                break;
            }
        }
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case UP:
                pacmanDirectionX = 0;
                pacmanDirectionY = -1;
                break;
            case DOWN:
                pacmanDirectionX = 0;
                pacmanDirectionY = 1;
                break;
            case LEFT:
                pacmanDirectionX = -1;
                pacmanDirectionY = 0;
                break;
            case RIGHT:
                pacmanDirectionX = 1;
                pacmanDirectionY = 0;
                break;
            default:
                break;
        }
    }

    private void movePlayer() {
        int nextX = playerX + pacmanDirectionX;
        int nextY = playerY + pacmanDirectionY;

        if (nextX >= 0 && nextX < WIDTH && nextY >= 0 && nextY < HEIGHT) {
            char nextTile = MAP[nextY].charAt(nextX);

            if (nextTile != '#') {
                playerX = nextX;
                playerY = nextY;
                pacman.setX(playerX * TILE_SIZE);
                pacman.setY(playerY * TILE_SIZE);
                createDots();
            }
        }
    }

    private void checkCollisions() {
        for (Node dot : dots.getChildren()) {
            Rectangle rect = (Rectangle) dot;
            if (pacman.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                dots.getChildren().remove(dot);
                break;
            }
        }
    } */

    public static void main(String[] args) {
        launch(args);
    }
}

