package Game1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.awt.*;

class Sprite1 {
    private double x;
    private double y;
    private double speed;
    private boolean intersected;
    private int id;
    private int SPRITE_SIZE = 50;
    private int SPRITE_HEIGHT = 80;
    private int direction = 1;
    private boolean lostLife = false;
    private Image[] characters = new Image[6];
    private boolean idNotChanged = false;
    private boolean intersectedSetAlready = false;

    public Sprite1(double x, double y, double speed, int id, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        if (id % 2 == 0) {
            //character = new Image("file:./img/sunglasses1_neutral.png");
            this.id = 0;
        }
        else {
            //character = new Image("file:./img/sunglasses2_neutral.png");
            this.id = 1;
        }
        characters[0] = new Image("file:./img/sunglasses1_neutral.png");
        characters[1] = new Image("file:./img/sunglasses2_neutral.png");
        characters[2] = new Image("file:./img/sunglasses1_sad.png");
        characters[3] = new Image("file:./img/sunglasses2_sad.png");
        characters[4] = new Image("file:./img/sunglasses1_happy.png");
        characters[5] = new Image("file:./img/sunglasses2_happy.png");
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isIntersected() {
        return intersected;
    }

    public void setIntersected(boolean intersected) {
        if (!intersectedSetAlready) {
            this.intersected = intersected;
            intersectedSetAlready = true;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(characters[id], x, y, SPRITE_SIZE, SPRITE_HEIGHT);
    }

    public void setLostLife(boolean lostLife) {
        this.lostLife = lostLife;
    }

    public boolean getLostLife() {
        return lostLife;
    }

    public void setId(int id) {
        if (!idNotChanged) {
            this.id = id;
            idNotChanged = true;
        }
    }

    public int getId() {
        return id;
    }

}
