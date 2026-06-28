package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  private String expression;

  public Tokenizer(String expression) {
    this.expression = expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();
    StringBuilder number = new StringBuilder();
    int length = expression.length();
    boolean hasDecimal = false;

    for (int i = 0; i < length; i++) {
      char c = expression.charAt(i);

      if (Character.isWhitespace(c)) {
        if (!number.isEmpty()) {
          tokens.add(buildNumber(number));
          hasDecimal = false;
        }
        continue;
      }

      if (Character.isDigit(c)) {
        number.append(c);

        if (i + 1 < length && Character.isWhitespace(expression.charAt(i + 1))) {
          tokens.add(buildNumber(number));
          hasDecimal = false;
        }

        continue;
      }

      if (c == '.') {
        if (hasDecimal) {
          throw new IllegalArgumentException("Invalid number");
        }

        hasDecimal = true;
        number.append(c);

        if (i + 1 < length && Character.isWhitespace(expression.charAt(i + 1))) {
          tokens.add(buildNumber(number));
          hasDecimal = false;
        }

        continue;
      }

      if (!number.isEmpty()) {
        tokens.add(buildNumber(number));
        hasDecimal = false;
      }

      if ("+-*/^".indexOf(c) != -1) {
        tokens.add(
            new Token(
                TokenType.OPERATOR,
                String.valueOf(c)
            )
        );
      } else if (c == '(') {
        tokens.add(
            new Token(
                TokenType.LEFT_PAREN,
                "("
            )
        );
      } else if (c == ')') {
        tokens.add(
            new Token(
                TokenType.RIGHT_PAREN,
                ")"
            )
        );
      } else {
        throw new IllegalArgumentException("Invalid character: " + c);
      }
    }
    if (!number.isEmpty()) {
      tokens.add(
          new Token(
              TokenType.NUMBER,
              number.toString()
          )
      );
    }

    return tokens;
  }

  private Token buildNumber(StringBuilder number) {
    Token t =new Token(
            TokenType.NUMBER,
            number.toString()
        );
    number.setLength(0);
    return t;
  }
}
