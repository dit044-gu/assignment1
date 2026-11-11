public class CarTest {
    private static void reportResult(String testName, boolean success) {
        IO.println((success ? "SUCCESS: " : "FAILURE: ") + testName);
    }

    /** Tests whether a Saab95 with turbo enabled
     *  gains more speed than one without.
     */
    private static void testTurbo() {
        Saab95 slowSaab = new Saab95();
        Saab95 fastSaab = new Saab95();
        fastSaab.setTurboOn();
        slowSaab.setTurboOff();
        fastSaab.gas(0.5);
        slowSaab.gas(0.5);
        boolean result = slowSaab.getCurrentSpeed() < fastSaab.getCurrentSpeed();
        reportResult("Turbo yields more speed", result);
    }

    /** startEngine should set the speed to 0.1. */
    private static void testStartEngine() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        boolean result = volvo.getCurrentSpeed() == 0.1;
        reportResult("startEngine sets speed to 0.1", result);
    }

    /** Volvo gas(1) should give speed 1.25. */
    private static void testVolvoGas() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1);
        boolean result = volvo.getCurrentSpeed() == 1.25;
        reportResult("Volvo gas(1) gives speed 1.25", result);
    }

    /** Facing North, turnRight then move should increase x, y stays 0. */
    private static void testTurnRightThenMove() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1);
        volvo.turnRight();
        volvo.move();
        boolean result = volvo.x == 1.25 && volvo.y == 0;
        reportResult("turnRight + move increases x only", result);
    }

    /** gas(2), move North, then turnLeft: position stays (0, 2.5) and the car now faces West. */
    private static void testMoveThenTurnLeft() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(2);
        volvo.move();
        volvo.turnLeft();
        boolean result = volvo.x == 0 && volvo.y == 2.5 && volvo.direction.equals("West");
        reportResult("move North then turnLeft: position unchanged, now facing West", result);
    }


    /** gas(2) then brake(1) should leave the speed at 1.25. */
    private static void testGasTwoThenBrakeOne() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(2);
        volvo.brake(1);
        boolean result = volvo.getCurrentSpeed() == 1.25;
        reportResult("gas(2) then brake(1) gives speed 1.25", result);
    }

    /** stopEngine should set the speed to 0. */
    private static void testStopEngine() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(3);
        volvo.stopEngine();
        boolean result = volvo.getCurrentSpeed() == 0;
        reportResult("stopEngine sets speed to 0", result);
    }

    static void main() {
        testTurbo();
        testStartEngine();
        testVolvoGas();
        testTurnRightThenMove();
        testMoveThenTurnLeft();
        testGasTwoThenBrakeOne();
        testStopEngine();

    }
}
