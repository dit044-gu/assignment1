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

    /** gas(1.5) or gas(-1) should be rejected
     * inheritance by both, choose anyone to test*/
    private static void testInvalidGas() {
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
    }

    /** brake(2) or brake(-1.5) should be rejected
     * inheritance by both, choose anyone to test */
    private static void testInvalidBrake() {
        Volvo240 volvo = new Volvo240();

        boolean brakeRejectAboveOne = false;
        boolean brakeRejectBelowZero = false;

        try {
            volvo.gas(2);
        } catch (IllegalArgumentException e) {
            brakeRejectAboveOne = true;
        }
        try {
            volvo.gas(-1.5);
        } catch (IllegalArgumentException e) {
            brakeRejectBelowZero = true;
        }

        reportResult("Brake rejects above one", brakeRejectAboveOne);
        reportResult("Brake rejects below zero", brakeRejectBelowZero);
    }

    /** Volvo 0<= currentSpeed <= enginePower = 100 */
static void testSpeedLimitVolvo () {
    Volvo240 volvo = new Volvo240();

    volvo.currentSpeed = 100;
    volvo.gas(1);
    boolean maxResult = volvo.getCurrentSpeed() <= volvo.enginePower;
    reportResult("Volvo current speed does not exceed engine power", maxResult);

    volvo.currentSpeed = 0;
    volvo.brake(1);
    boolean minResult = volvo.getCurrentSpeed() >= 0;
    reportResult("Volvo current speed does not go below 0", minResult);
}

    /** Saab 0<= currentSpeed <= enginePower = 125 */
static void testSpeedLimitSaab () {
    Saab95 saab = new Saab95();

    saab.currentSpeed = 125;
    saab.gas(1);
    boolean maxResult = saab.getCurrentSpeed() <= saab.enginePower;
    reportResult("Saab current speed does not exceed engine power", maxResult);

    saab.currentSpeed = 0;
    saab.brake(1);
    boolean minResult = saab.getCurrentSpeed() >= 0;
    reportResult("Saab current speed does not go below 0", minResult);
}

/** gas cannot decrease speed
 *  inheritance by both, choose anyone to test */
static void testGasNotDecreaseSpeed() {
    Saab95 saab = new Saab95();
    double oldSpeed = saab.getCurrentSpeed();
    saab.gas(-1);
    double newSpeed = saab.getCurrentSpeed();
    boolean gasNotDecreaseSpeed = newSpeed >= oldSpeed;
    reportResult("Gas not decrease speed", gasNotDecreaseSpeed);
}

/** Junit
 * brake cannot increase speed
 * inheritance by both, choose anyone to test */
@Test
static void testBrakeNotIncreaseSpeed() {
    Volvo240 volvo = new Volvo240();
    double oldSpeed = volvo.getCurrentSpeed();
    volvo.brake(-1);
    assertTrue(volvo.getCurrentSpeed() >= oldSpeed);
}


    static void main() {
        testTurbo();
        testStartEngine();
        testVolvoGas();
        testTurnRightThenMove();
        testMoveThenTurnLeft();
        testGasTwoThenBrakeOne();
        testStopEngine();

        testInvalidGas();
        testInvalidBrake();
        testSpeedLimitVolvo();
        testSpeedLimitSaab ();
        testGasNotDecreaseSpeed();
        testBrakeNotIncreaseSpeed();

    }
}
