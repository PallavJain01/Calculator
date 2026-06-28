package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenizerTest {

  @Test
  void tokenizeOperators() {
    Tokenizer tokenizer = new Tokenizer("+-*/^");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(5, tokens.size());

    assertEquals(new Token(TokenType.OPERATOR, "+"), tokens.get(0));
    assertEquals(new Token(TokenType.OPERATOR, "-"), tokens.get(1));
    assertEquals(new Token(TokenType.OPERATOR, "*"), tokens.get(2));
    assertEquals(new Token(TokenType.OPERATOR, "/"), tokens.get(3));
    assertEquals(new Token(TokenType.OPERATOR, "^"), tokens.get(4));
  }

  @Test
  void tokenizeNumbers() {
    Tokenizer tokenizer = new Tokenizer("1 0.1 1.0 11 11.11");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(5, tokens.size());

    assertEquals(new Token(TokenType.NUMBER, "1"), tokens.get(0));
    assertEquals(new Token(TokenType.NUMBER, "0.1"), tokens.get(1));
    assertEquals(new Token(TokenType.NUMBER, "1.0"), tokens.get(2));
    assertEquals(new Token(TokenType.NUMBER, "11"), tokens.get(3));
    assertEquals(new Token(TokenType.NUMBER, "11.11"), tokens.get(4));
  }

  @Test
  void tokenizeSimpleExpression() {
    Tokenizer tokenizer = new Tokenizer("10 + 2");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(3, tokens.size());

    assertEquals(new Token(TokenType.NUMBER, "10"), tokens.get(0));
    assertEquals(new Token(TokenType.OPERATOR, "+"), tokens.get(1));
    assertEquals(new Token(TokenType.NUMBER, "2"), tokens.get(2));
  }

  @Test
  void tokenizeParentheses() {
    Tokenizer tokenizer = new Tokenizer("(1+2)");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(5, tokens.size());

    assertEquals(new Token(TokenType.LEFT_PAREN, "("), tokens.get(0));
    assertEquals(new Token(TokenType.NUMBER, "1"), tokens.get(1));
    assertEquals(new Token(TokenType.OPERATOR, "+"), tokens.get(2));
    assertEquals(new Token(TokenType.NUMBER, "2"), tokens.get(3));
    assertEquals(new Token(TokenType.RIGHT_PAREN, ")"), tokens.get(4));
  }

  @Test
  void tokenizeIgnoresWhitespace() {
    Tokenizer tokenizer = new Tokenizer("   12   +    5   ");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(3, tokens.size());

    assertEquals(new Token(TokenType.NUMBER, "12"), tokens.get(0));
    assertEquals(new Token(TokenType.OPERATOR, "+"), tokens.get(1));
    assertEquals(new Token(TokenType.NUMBER, "5"), tokens.get(2));
  }

  @Test
  void invalidCharacterThrowsException() {
    Tokenizer tokenizer = new Tokenizer("2+a");

    assertThrows(
        IllegalArgumentException.class,
        tokenizer::tokenize
    );
  }

  @Test
  void invalidDecimalThrowsException() {
    Tokenizer tokenizer = new Tokenizer("1..5");

    assertThrows(
        IllegalArgumentException.class,
        tokenizer::tokenize
    );
  }

  @Test
  void emptyExpression() {
    Tokenizer tokenizer = new Tokenizer("");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(0, tokens.size());
  }

  @Test
  void tokenizeLeadingDecimal() {
    Tokenizer tokenizer = new Tokenizer(".5");

    List<Token> tokens = tokenizer.tokenize();

    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.NUMBER, ".5"), tokens.get(0));
  }
}
