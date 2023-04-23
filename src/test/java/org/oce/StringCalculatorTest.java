package org.oce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.oce.exception.InvalidOperationCaseException;
import org.oce.exception.InvalidStringOperationException;
import org.oce.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    @DisplayName("Case1: 1+1 = 1")
    void shouldPerformCase01() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(1, stringCalculator.evaluateStringOperation("1+1"));
    }

    @Test
    @DisplayName("Case2: 1 + 2 = 3")
    void shouldPerformCase02() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(3, stringCalculator.evaluateStringOperation("1 + 2"));
    }

    @Test
    @DisplayName("Case3: 1 + -1 = 0")
    void shouldPerformCase03() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(0, stringCalculator.evaluateStringOperation("1 + -1"));
    }

    @Test
    @DisplayName("Case4: -1 - -1 = 0")
    void shouldPerformCase04() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(0, stringCalculator.evaluateStringOperation("-1 - -1"));
    }

    @Test
    @DisplayName("Case5: 5-4 = 1")
    void shouldPerformCase05() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(1, stringCalculator.evaluateStringOperation("5-4"));
    }

    @Test
    @DisplayName("Case6: 5*2 = 10")
    void shouldPerformCase06() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(10, stringCalculator.evaluateStringOperation("5*2"));
    }

    @Test
    @DisplayName("Case7: (2+5)*3 = 21")
    void shouldPerformCase07() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(21, stringCalculator.evaluateStringOperation("(2+5)*3"));
    }

    @Test
    @DisplayName("Case8: 10/2 = 5")
    void shouldPerformCase08() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(5, stringCalculator.evaluateStringOperation("10/2"));
    }

    @Test
    @DisplayName("Case9: 2+2*5+5 = 17")
    void shouldPerformCase09() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(17, stringCalculator.evaluateStringOperation("2+2*5+5"));
    }

    @Test
    @DisplayName("Case10: 2.8*3-1 = 7.4")
    void shouldPerformCase10() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(7.4, stringCalculator.evaluateStringOperation("2.8*3-1"));
    }

    @Test
    @DisplayName("Case11: 2^8 = 256")
    void shouldPerformCase11() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(256, stringCalculator.evaluateStringOperation("2^8"));
    }

    @Test
    @DisplayName("Case12: 2^8*5-1 = 1279")
    void shouldPerformCase12() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(1279, stringCalculator.evaluateStringOperation("2^8*5-1"));
    }

    @Test
    @DisplayName("Case13: sqrt(4) = 2")
    void shouldPerformCase13() throws InvalidStringOperationException, InvalidOperationCaseException {
        Assertions.assertEquals(2, stringCalculator.evaluateStringOperation("sqrt(4)"));
    }

    @Test
    @DisplayName("Case14: 1/0 = Erreur")
    void shouldPerformCase14() {
        assertThrows(InvalidOperationCaseException.class,
                () -> stringCalculator.evaluateStringOperation("1/0"),
                "Expected evaluateStringOperation() to throw InvalidOperationCaseException");
    }

    @Test
    @DisplayName("Throw InvalidStringOperationException when operation has invalid characters")
    void shouldInvalidStringOperationException_whenInvalidCharsExists() {
        InvalidStringOperationException exception = assertThrows(InvalidStringOperationException.class,
                () -> stringCalculator.evaluateStringOperation("5,8+6*9:7"),
                "Expected evaluateStringOperation() to throw InvalidStringOperationException");
        assertEquals(String.format(Constants.Message.WRONG_CHARS, ",:"), exception.getMessage());
    }

    @Test
    @DisplayName("Throw InvalidStringOperationException when string operation is empty")
    void shouldInvalidStringOperationException_whenOperationEmpty() {
        InvalidStringOperationException exception = assertThrows(InvalidStringOperationException.class,
                () -> stringCalculator.evaluateStringOperation(""),
                "Expected evaluateStringOperation() to throw InvalidStringOperationException");
        assertEquals(Constants.Message.EMPTY_STRING, exception.getMessage());
    }

    @Test
    @DisplayName("Throw InvalidStringOperationException when string operation contains the same consecutive operator")
    void shouldInvalidStringOperationException_whenHasTheSameConsecutiveOperator() {
        InvalidStringOperationException exception = assertThrows(InvalidStringOperationException.class,
                () -> stringCalculator.evaluateStringOperation("5++6"),
                "Expected evaluateStringOperation() to throw InvalidStringOperationException");
        assertEquals(Constants.Message.SAME_CONSECUTIVE_OPERATOR, exception.getMessage());
    }
}
