package Game1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

class Sprite1 {
    private double x;
    private double y;
    private double speed;
    private boolean intersected;
    private Color color;
    private int id;
    private int SPRITE_SIZE = 40;
    private int direction = 1;
    private boolean lostLife = false;
    private Image character;

    public Sprite1(double x, double y, double speed, Color color, int id, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        this.id = id;
        this.direction = direction;
        if (id % 2 == 0) {
            //character = new Image("file:./img/sunglasses1_neutral.png");

        }
        else {
            //character = new Image("file:./img/sunglasses2_neutral.png");
        }
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
        this.intersected = intersected;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
    }

    public void setLostLife(boolean lostLife) {
        this.lostLife = lostLife;
    }

    public boolean getLostLife() {
        return lostLife;
    }

}
