import java.awt.*;

public class Cars implements Movable{
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double x;
    private double y;
    private String direction;

    public Cars(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
        this.x = 0;
        this.y = 0;
        this.direction = "up";
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public double speedFactor() {
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;

        if (currentSpeed > enginePower){
            currentSpeed = enginePower;
        }
    }

    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;

        if (currentSpeed < 0) {
            currentSpeed = 0;
        }
    }

    public void gas(double amount) {
        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
    }

    @Override
    public void move() {
        if (direction.equals("up")) {
            y+= currentSpeed;
        }
        else if (direction.equals("down")) {
            y -= currentSpeed;
        }
        else if (direction.equals("right")) {
            x += currentSpeed;
        }
        else if (direction.equals("left")) {
            x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {

        if (direction.equals("up")) {
            direction = "left";
        }
        else if (direction.equals("left")) {
            direction = "down";
        }
        else if (direction.equals("down")) {
            direction = "right";
        }
        else if (direction.equals("right")) {
            direction = "up";
        }
    }


    @Override
    public void turnRight() {
        if (direction.equals("up")) {
            direction = "right";
        }
        else if (direction.equals("right")) {
            direction = "down";
        }
        else if (direction.equals("down")) {
            direction = "left";
        }
        else if (direction.equals("left")) {
            direction = "up";
        }
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

}
