package io.github.pallavjain01.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

  @Test
  void evaluateAddition() {
    assertEquals(5.0, Calculator.evaluate("2+3"));
  }

  @Test
  void evaluateSubtraction() {
    assertEquals(4.0, Calculator.evaluate("7-3"));
  }

  @Test
  void evaluateMultiplication() {
    assertEquals(20.0, Calculator.evaluate("4*5"));
  }

  @Test
  void evaluateDivision() {
    assertEquals(5.0, Calculator.evaluate("10/2"));
  }

  @Test
  void evaluateExponentiation() {
    assertEquals(8.0, Calculator.evaluate("2^3"));
  }

  @Test
  void evaluateOperatorPrecedence() {
    assertEquals(14.0, Calculator.evaluate("2+3*4"));
  }

  @Test
  void evaluateParentheses() {
    assertEquals(20.0, Calculator.evaluate("(2+3)*4"));
  }

  @Test
  void evaluateNestedParentheses() {
    assertEquals(15.0, Calculator.evaluate("(2+3)*(4-1)"));
  }

  @Test
  void evaluateRightAssociativeExponent() {
    assertEquals(512.0, Calculator.evaluate("2^3^2"));
  }

  @Test
  void evaluateDecimalExpression() {
    assertEquals(5.0, Calculator.evaluate("3.5+1.5"));
  }

  @Test
  void evaluateComplexExpression() {
    assertEquals(3.5, Calculator.evaluate("3+4*2/(1-5)^2"));
  }

  @Test
  void divideByZeroThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("5/0"));
  }

  @Test
  void mismatchedLeftParenthesisThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("(2+3"));
  }

  @Test
  void mismatchedRightParenthesisThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("2+3)"));
  }

  @Test
  void invalidCharacterThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("2+a"));
  }

  @Test
  void invalidDecimalThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("1..5"));
  }

  @Test
  void incompleteExpressionThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate("2+"));
  }

  @Test
  void emptyExpressionThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Calculator.evaluate(""));
  }
}