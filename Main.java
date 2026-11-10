import java.awt.*;

class Main{
    public static void main(String[] args) {
        Car car1 = new Car(4, Color.black, 240, "Supra");
        System.out.printf("Car: %f engine power, %d doors.\n", car1.getEnginePower(), car1.getNrDoors());
        car1.gas(100);
        System.out.printf("%f\n", car1.getCurrentSpeed());

        Saab95 car2 = new Saab95();
        Saab95 car3 = new Saab95();
        car2.gas(100);
        System.out.printf("%f\n", car2.getCurrentSpeed());
        car3.setTurboOn();
        car3.gas(100);
        System.out.printf("%f\n", car3.getCurrentSpeed());

        Volvo240 car4 = new Volvo240();
        car4.gas(100);
        System.out.printf("%f\n", car4.getCurrentSpeed());
    }
}