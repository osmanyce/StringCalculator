package org.oce.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.oce.exception.InvalidStringOperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {
    public static double convertStringToNumber(String str) throws InvalidStringOperationException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            log.info(nfe.getLocalizedMessage());
            throw new InvalidStringOperationException(String.format(Constants.Message.INVALID_NUMBER, str));
        }
    }

    public static double roundNumber(Double value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static boolean hasSameConsecutiveOperator(String stringOperator) {
        Pattern pattern = Pattern.compile(Constants.Regex.CONSECUTIVE_OPERATORS);
        Matcher matcher = pattern.matcher(stringOperator);
        return matcher.find();
    }
}
