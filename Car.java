import java.awt.*;


public abstract class Car{
    private int nrDoors;
    private double enginePower;
    private Color color;
    private String modelName;
    private double currentSpeed;
    public double stopEngine;
    public Car(int nrDoors, Color color, double enginePower, String modelName){
    this.nrDoors = nrDoors;
    this.enginePower = enginePower;
    this.color = color;
    this.modelName = modelName;
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
        color = clr;   } 
   
}