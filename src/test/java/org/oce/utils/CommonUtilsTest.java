package org.oce.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.oce.exception.InvalidStringOperationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommonUtilsTest {
    @Test
    @DisplayName("Convert string to number successfully")
    void shouldConvertStringToNumber() throws InvalidStringOperationException {
        Assertions.assertEquals(55.55, CommonUtils.convertStringToNumber("55.55"));
    }

    @Test
    @DisplayName("Get error when trying to convert invalid string to number")
    void shouldGetErrorWhenInvalidStringIsSent() {
        assertThrows(InvalidStringOperationException.class,
                () -> CommonUtils.convertStringToNumber("789abc"),
                "Expected convertStringToNumber() to throw InvalidStringOperationException");
    }

    @Test
    @DisplayName("Round number to 2 places successfully")
    void shouldRoundNumber() {
        Assertions.assertEquals(3.14, CommonUtils.roundNumber(Math.PI));
    }

    @Test
    @DisplayName("Get error when trying to convert invalid string to number")
    void shouldGetErrorWhenNullValueIsSent() {
        assertThrows(IllegalArgumentException.class,
                () -> CommonUtils.roundNumber(null),
                "Expected roundNumber() to throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Has the same consecutive operator")
    void shouldHasSameConsecutiveOperator() {
        Assertions.assertTrue(CommonUtils.hasSameConsecutiveOperator("20++3"));
    }

    @Test
    @DisplayName("Hasn't the same consecutive operator")
    void shouldHasNotSameConsecutiveOperator() {
        Assertions.assertFalse(CommonUtils.hasSameConsecutiveOperator("5*5"));
    }
}
