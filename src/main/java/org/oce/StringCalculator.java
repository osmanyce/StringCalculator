package org.oce;

import lombok.extern.java.Log;
import org.oce.exception.InvalidOperationCaseException;
import org.oce.exception.InvalidStringOperationException;
import org.oce.utils.CommonUtils;
import org.oce.utils.Constants;
import org.oce.utils.Operator;

import java.util.ArrayDeque;
import java.util.Objects;

@Log
public class StringCalculator {
    public double evaluateStringOperation(String stringOperation) throws InvalidStringOperationException, InvalidOperationCaseException {
        log.info(String.format("Initial value: %s", stringOperation));
        isValidOperation(stringOperation);
        String rebuiltStrOperation = rebuildStringOperation(stringOperation);
        log.info(String.format("Rebuilt stringOperation: %s", rebuiltStrOperation));

        if (rebuiltStrOperation.equals(Constants.Regex.ALGEBRA_CASE)) {
            // algebra case: true + true = true
            return 1;
        }

        ArrayDeque<Double> numberStack = new ArrayDeque<>();
        ArrayDeque<Operator> operatorStack = new ArrayDeque<>();
        StringBuilder numberIndex = new StringBuilder();

        for (int i = 0; i < rebuiltStrOperation.length(); i++) {
            char charIndex = rebuiltStrOperation.charAt(i);
            if (Character.isDigit(charIndex) || charIndex == '.') {
                numberIndex.append(charIndex);
            } else {
                numberIndex = extractOperators(numberStack, operatorStack, numberIndex, charIndex);
            }
        }

        if (numberIndex.length() > 0) {
            numberStack.push(CommonUtils.convertStringToNumber(numberIndex.toString()));
        }

        while (!operatorStack.isEmpty()) {
            buildAndExecuteCalculation(numberStack, operatorStack);
        }

        return CommonUtils.roundNumber(numberStack.pop());
    }

    private void isValidOperation(String stringOperation) throws InvalidStringOperationException {
        if (Objects.isNull(stringOperation) || stringOperation.isBlank()) {
            throw new InvalidStringOperationException(Constants.Message.EMPTY_STRING);
        }
        String wrongChars = stringOperation.replaceAll(Constants.Regex.POSSIBLE_CHARS, "");
        if (!wrongChars.isBlank()) {
            throw new InvalidStringOperationException(String.format(Constants.Message.WRONG_CHARS, wrongChars));
        }
    }

    private String rebuildStringOperation(String stringOperation) throws InvalidStringOperationException {
        String cleanedStrOperation = stringOperation.replaceAll("\\s+", "").toLowerCase();

        if (stringOperation.contains(Constants.Regex.SQRT_NAME)) {
            cleanedStrOperation = cleanedStrOperation.replaceAll(Constants.Regex.SQRT_NAME, String.valueOf(Operator.SQRT.getSymbol()));
        }
        if (cleanedStrOperation.contains(Constants.Regex.DOUBLE_SUB)) {
            cleanedStrOperation = cleanedStrOperation.replaceAll(Constants.Regex.DOUBLE_SUB, String.valueOf(Operator.ADD.getSymbol()));
        }
        if (CommonUtils.hasSameConsecutiveOperator(cleanedStrOperation)) {
            throw new InvalidStringOperationException(Constants.Message.SAME_CONSECUTIVE_OPERATOR);
        }
        return cleanedStrOperation;
    }

    private StringBuilder extractOperators(
            ArrayDeque<Double> numberStack, ArrayDeque<Operator> operatorStack, StringBuilder numberIndex, char charIndex
    ) throws InvalidOperationCaseException, InvalidStringOperationException {
        Operator operatorIndex = Operator.fromSymbol(charIndex);
        if (numberIndex.length() > 0) {
            numberStack.push(CommonUtils.convertStringToNumber(numberIndex.toString()));
            numberIndex = new StringBuilder();
        }
        if (operatorIndex == Operator.LEFT_PARENTHESIS) {
            operatorStack.push(operatorIndex);
        } else if (operatorIndex == Operator.RIGHT_PARENTHESIS) {
            while (operatorStack.peek() != Operator.LEFT_PARENTHESIS) {
                buildAndExecuteCalculation(numberStack, operatorStack);
            }
            operatorStack.pop();
        } else if (Operator.isSimpleOperator(operatorIndex)) {
            while (!operatorStack.isEmpty() && isCalculationPossible(operatorIndex, operatorStack.peek())) {
                buildAndExecuteCalculation(numberStack, operatorStack);
            }
            operatorStack.push(operatorIndex);
        }
        return numberIndex;
    }

    private boolean isCalculationPossible(Operator opIndex, Operator opStored) {
        if ((opIndex == Operator.POW || opIndex == Operator.SQRT) && (Operator.isBasicOperator(opStored))) {
            return false;
        }
        if ((opIndex == Operator.MULTIPLICATION || opIndex == Operator.DIVISION)
                && (opStored == Operator.ADD || opStored == Operator.SUB)) {
            return false;
        }
        return !Operator.isGroupOperator(opStored);
    }

    private void buildAndExecuteCalculation(ArrayDeque<Double> numberStack, ArrayDeque<Operator> operatorStack) throws InvalidOperationCaseException {
        double num2 = numberStack.pop();
        double num1 = numberStack.isEmpty() ? 0 : numberStack.pop();
        Operator op = operatorStack.pop();
        if (op == Operator.SQRT && !operatorStack.isEmpty()) {
            numberStack.push(num1);
        }
        double result = executeCalculationByOperator(num1, num2, op);
        numberStack.push(result);
    }

    private double executeCalculationByOperator(double num1, double num2, Operator op) throws InvalidOperationCaseException {
        switch (op) {
            case ADD:
                return num1 + num2;
            case SUB:
                return num1 - num2;
            case MULTIPLICATION:
                return num1 * num2;
            case DIVISION:
                if (num2 == 0) {
                    throw new InvalidOperationCaseException(Constants.Message.DIVISION_BY_ZERO);
                }
                return num1 / num2;
            case POW:
                return Math.pow(num1, num2);
            case SQRT:
                return Math.sqrt(num2);
            default:
                throw new InvalidOperationCaseException(String.format(Constants.Message.UNSUPPORTED_OPERATOR, op));
        }
    }
}

@Log
class Main {
    public static void main(String[] args) throws InvalidStringOperationException, InvalidOperationCaseException {
        StringCalculator calculator = new StringCalculator();
        double result = calculator.evaluateStringOperation("2^6+15");
        log.info("####### Result: " + result);
    }
}

