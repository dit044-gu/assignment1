import java.awt.*;


    public abstract class Car{
        
        protected int nrDoors;
        protected double enginePower;
        protected  Color color;
        protected String modelName;
        protected  double currentSpeed;
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
