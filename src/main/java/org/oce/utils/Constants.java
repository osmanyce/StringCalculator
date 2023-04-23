package org.oce.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Regex {
        public static final String POSSIBLE_CHARS = "(?i)sqrt|[\\-+*\\/\\^()\\s.]|\\d+\\.?\\d*";
        public static final String CONSECUTIVE_OPERATORS = "([\\^*/\\+\\-s])\\1+";
        public static final String SQRT_NAME = "sqrt";
        public static final String DOUBLE_SUB = "--";
        public static final String ALGEBRA_CASE = "1+1";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Message {
        public static final String EMPTY_STRING = "The string cannot be empty";
        public static final String WRONG_CHARS = "The string contains the following wrong characters: %s";
        public static final String INVALID_NUMBER = "Unable to perform operations with the number: %s";
        public static final String DIVISION_BY_ZERO = "Division by zero is not allowed";
        public static final String UNSUPPORTED_OPERATOR = "The %s operator is not yet supported";
        public static final String SAME_CONSECUTIVE_OPERATOR = "The string contains the same consecutive operator";
    }
}
