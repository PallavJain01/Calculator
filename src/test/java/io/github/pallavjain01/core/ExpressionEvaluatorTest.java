package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionEvaluatorTest {

  @Test
  void evaluateAddition() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "2"), new Token(TokenType.NUMBER, "3"), new Token(TokenType.OPERATOR, "+")));

    assertEquals(5.0, evaluator.evaluate());
  }

  @Test
  void evaluateSubtraction() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "7"), new Token(TokenType.NUMBER, "3"), new Token(TokenType.OPERATOR, "-")));

    assertEquals(4.0, evaluator.evaluate());
  }

  @Test
  void evaluateMultiplication() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "4"), new Token(TokenType.NUMBER, "5"), new Token(TokenType.OPERATOR, "*")));

    assertEquals(20.0, evaluator.evaluate());
  }

  @Test
  void evaluateDivision() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "10"), new Token(TokenType.NUMBER, "2"), new Token(TokenType.OPERATOR, "/")));

    assertEquals(5.0, evaluator.evaluate());
  }

  @Test
  void evaluateExponentiation() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "2"), new Token(TokenType.NUMBER, "3"), new Token(TokenType.OPERATOR, "^")));

    assertEquals(8.0, evaluator.evaluate());
  }

  @Test
  void evaluateComplexExpression() {
    // (2 + 3) * 4
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "2"), new Token(TokenType.NUMBER, "3"), new Token(TokenType.OPERATOR, "+"), new Token(TokenType.NUMBER, "4"), new Token(TokenType.OPERATOR, "*")));

    assertEquals(20.0, evaluator.evaluate());
  }

  @Test
  void evaluateDecimalNumbers() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "3.5"), new Token(TokenType.NUMBER, "1.5"), new Token(TokenType.OPERATOR, "+")));

    assertEquals(5.0, evaluator.evaluate());
  }

  @Test
  void divideByZeroThrowsException() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "5"), new Token(TokenType.NUMBER, "0"), new Token(TokenType.OPERATOR, "/")));

    assertThrows(IllegalArgumentException.class, evaluator::evaluate);
  }

  @Test
  void invalidPostfixExpressionThrowsException() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "2"), new Token(TokenType.OPERATOR, "+")));

    assertThrows(IllegalArgumentException.class, evaluator::evaluate);
  }

  @Test
  void emptyExpressionThrowsException() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of());

    assertThrows(IllegalArgumentException.class, evaluator::evaluate);
  }

  @Test
  void unknownOperatorThrowsException() {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(List.of(new Token(TokenType.NUMBER, "2"), new Token(TokenType.NUMBER, "3"), new Token(TokenType.OPERATOR, "%")));

    assertThrows(IllegalArgumentException.class, evaluator::evaluate);
  }
}