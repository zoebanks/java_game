package Game1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Sprite1 {
    private double x;
    private double y;
    private double speed;
    private boolean intersected;
    private Color color;
    private int id;
    private int SPRITE_SIZE = 40;
    private int direction = -1;

    public Sprite1(double x, double y, double speed, Color color, int id, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        this.id = id;
        this.direction = direction;
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

    public int getId() {
        return id;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
    }
}
