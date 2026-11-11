import java.awt.*;


    public abstract class Car implements Movable{
        protected int nrDoors;
        protected double enginePower;
        protected Color color;
        protected String modelName;
        protected double currentSpeed;
        protected double x;
        protected double y;
        protected String direction = "North";
    

    
    public Car(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
        this.x = 0;
        this.y = 0;
        

        }

        public void stopEngine(){
        currentSpeed = 0;    
        }
        public int getNrDoors() {
            return nrDoors;
        }
        public double getEnginePower() {
            return enginePower;
        }
        public Color getColor() {
            return color;
        }
        public void setColor(Color clr) {
            color = clr;   
        } 
        public double getCurrentSpeed(){
            return currentSpeed;
        }
        public void startEngine() {
            currentSpeed = 0.1;
        }
        protected abstract double speedFactor();
        
    public void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    public void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
        public void gas(double amount) {
        incrementSpeed(amount);
    }
    public void brake(double amount) {
        decrementSpeed(amount);
    }

    @Override 
    public void move(){
        switch (direction){
                case "North":
                y = currentSpeed + y;
                break;
                case "South":
                y = y - currentSpeed;
                break;
                case "West":
                x = x - currentSpeed;
                break;
                case "East":
                x = currentSpeed + x;
                break; 
        }
    }

    @Override 
    public void turnLeft(){
        switch (direction){
            case "North":
            direction = "West";
            break;
            case "West":
            direction = "South";
            break;
            case "South":
            direction = "East";
            break;
            case "East":
            direction = "North";
            break;

        }
    }
    
    @Override 
    public void turnRight(){
        switch (direction){
            case "North":
            direction = "East";
            break;
            case "East":
            direction = "South";
            break;
            case "South":
            direction = "West";
            break;
            case "West":
            direction = "North";
            break;

        }
    }
    }
