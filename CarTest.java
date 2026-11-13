import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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

    /** gas(1.5) or gas(-1) should be rejected, become gas(0)*/
    private static void testInvalidGas(Car car) {
        car.gas(1);
        double oldSpeed = car.getCurrentSpeed();
        car.gas(2);
        boolean gasRejectAboveOne = car.getCurrentSpeed() == oldSpeed;
        car.gas(-1);
        boolean gasRejectBelowZero = car.getCurrentSpeed() == oldSpeed;
        reportResult(car.getModelName() + " rejects gas above 1", gasRejectAboveOne);
        reportResult(car.getModelName() + " rejects gas below 0", gasRejectBelowZero);
    }

    /** brake(2) or brake(-1.5) should be rejected , become gas(0)*/
    private static void testInvalidBrake(Car car) {
        car.brake(1);
        double oldSpeed = car.getCurrentSpeed();
        car.brake(2);
        boolean brakeRejectAboveOne = car.getCurrentSpeed() == oldSpeed;
        car.brake(-1);
        boolean brakeRejectBelowZero = car.getCurrentSpeed() == oldSpeed;
        reportResult(car.getModelName() + " rejects brake above 1", brakeRejectAboveOne);
        reportResult(car.getModelName() + " rejects brake below 0", brakeRejectBelowZero);
    }

    /** 0<= currentSpeed <= enginePower */
    static void testSpeedLimit (Car car) {
        double enginePower = car.getEnginePower();
        car.setCurrentSpeed(enginePower);
        car.gas(1);
        boolean maxResult = car.getCurrentSpeed() <= car.getEnginePower();
        reportResult(car.getModelName() + " current speed does not exceed engine power", maxResult);

        car.setCurrentSpeed(0);
        car.brake(1);
        boolean minResult = car.getCurrentSpeed() >= 0;
        reportResult(car.getModelName() + " current speed does not go below 0", minResult);
    }

    /** gas cannot decrease speed */
    static void testGasNotDecreaseSpeed(Car car) {
        double oldSpeed = car.getCurrentSpeed();
        car.gas(0.5);
        double newSpeed = car.getCurrentSpeed();
        boolean gasNotDecreaseSpeed = newSpeed >= oldSpeed;
        reportResult(car.getModelName() + " gas not decrease speed", gasNotDecreaseSpeed);
    }

    /** Junit
     * brake cannot increase speed
     * inheritance by both, choose anyone to test */
    @Test
    void testBrakeNotIncreaseSpeed() {
        Volvo240 volvo = new Volvo240();
        volvo.gas(1);
        double oldSpeed = volvo.getCurrentSpeed();
        volvo.brake(0.2);
        assertTrue(volvo.getCurrentSpeed() <= oldSpeed);
    }

    static void main() {
        testTurbo();
        testStartEngine();
        testVolvoGas();
        testTurnRightThenMove();
        testMoveThenTurnLeft();
        testGasTwoThenBrakeOne();
        testStopEngine();

        testInvalidGas(new Volvo240());
        testInvalidBrake(new Saab95());
        testSpeedLimit (new Volvo240());
        testSpeedLimit (new Saab95());
        testGasNotDecreaseSpeed(new Volvo240());
    }
}



/** private static void testInvalidGas() {
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();

    boolean volvoRejectAboveOne = false;
    boolean volvoRejectBelowZero = false;
    boolean saabRejectAboveOne = false;
    boolean saabRejectBelowZero = false;

    try {
        volvo.gas(1.5);
    } catch (IllegalArgumentException e) {
        volvoRejectAboveOne = true;
    }
    try {
        volvo.gas(-1);
    } catch (IllegalArgumentException e) {
        volvoRejectBelowZero = true;
    }
    try {
        saab.gas(1.5);
    } catch (IllegalArgumentException e) {
        saabRejectAboveOne = true;
    }
    try {
        saab.gas(-1);
    } catch (IllegalArgumentException e) {
        saabRejectBelowZero = true;
    }

    reportResult("Volvo rejects gas above 1", volvoRejectAboveOne);
    reportResult("Volvo rejects gas below 0",  volvoRejectBelowZero);
    reportResult("Saab rejects gas above 1", saabRejectAboveOne);
    reportResult("Saab rejects gas below 0", saabRejectBelowZero);
} */

    /**
    private static void testInvalidBrake() {
        Volvo240 volvo = new Volvo240();

        boolean brakeRejectAboveOne = false;
        boolean brakeRejectBelowZero = false;

        try {
            volvo.brake(2);
        } catch (IllegalArgumentException e) {
            brakeRejectAboveOne = true;
        }
        try {
            volvo.brake(-1.5);
        } catch (IllegalArgumentException e) {
            brakeRejectBelowZero = true;
        }

        reportResult("Brake rejects above one", brakeRejectAboveOne);
        reportResult("Brake rejects below zero", brakeRejectBelowZero);
    } */

