package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotationEvaluatorTest {

  @Test
  void infixToPostfixSimpleAddition() {
    List<Token> infix = List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "3")
    );

    List<Token> postfix = NotationEvaluator.infixToPostfix(infix);

    assertEquals(List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.OPERATOR, "+")
    ), postfix);
  }

  @Test
  void infixToPostfixOperatorPrecedence() {
    List<Token> infix = List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.OPERATOR, "*"),
        new Token(TokenType.NUMBER, "4")
    );

    List<Token> postfix = NotationEvaluator.infixToPostfix(infix);

    assertEquals(List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.NUMBER, "4"),
        new Token(TokenType.OPERATOR, "*"),
        new Token(TokenType.OPERATOR, "+")
    ), postfix);
  }

  @Test
  void infixToPostfixParentheses() {
    List<Token> infix = List.of(
        new Token(TokenType.LEFT_PAREN, "("),
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.RIGHT_PAREN, ")"),
        new Token(TokenType.OPERATOR, "*"),
        new Token(TokenType.NUMBER, "4")
    );

    List<Token> postfix = NotationEvaluator.infixToPostfix(infix);

    assertEquals(List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "4"),
        new Token(TokenType.OPERATOR, "*")
    ), postfix);
  }

  @Test
  void infixToPostfixExponentIsRightAssociative() {
    List<Token> infix = List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "^"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.OPERATOR, "^"),
        new Token(TokenType.NUMBER, "2")
    );

    List<Token> postfix = NotationEvaluator.infixToPostfix(infix);

    assertEquals(List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "^"),
        new Token(TokenType.OPERATOR, "^")
    ), postfix);
  }

  @Test
  void infixToPostfixNestedParentheses() {
    List<Token> infix = List.of(
        new Token(TokenType.LEFT_PAREN, "("),
        new Token(TokenType.NUMBER, "1"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.LEFT_PAREN, "("),
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "*"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.RIGHT_PAREN, ")"),
        new Token(TokenType.RIGHT_PAREN, ")")
    );

    List<Token> postfix = NotationEvaluator.infixToPostfix(infix);

    assertEquals(List.of(
        new Token(TokenType.NUMBER, "1"),
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.OPERATOR, "*"),
        new Token(TokenType.OPERATOR, "+")
    ), postfix);
  }

  @Test
  void mismatchedLeftParenthesisThrowsException() {
    List<Token> infix = List.of(
        new Token(TokenType.LEFT_PAREN, "("),
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "3")
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> NotationEvaluator.infixToPostfix(infix)
    );
  }

  @Test
  void mismatchedRightParenthesisThrowsException() {
    List<Token> infix = List.of(
        new Token(TokenType.NUMBER, "2"),
        new Token(TokenType.OPERATOR, "+"),
        new Token(TokenType.NUMBER, "3"),
        new Token(TokenType.RIGHT_PAREN, ")")
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> NotationEvaluator.infixToPostfix(infix)
    );
  }
}