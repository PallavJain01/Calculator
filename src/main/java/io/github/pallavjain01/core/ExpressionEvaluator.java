package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;

import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {
  private List<Token> tokens;

  public ExpressionEvaluator(List<Token> tokens) {
    this.tokens = tokens;
  }

  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  public double evaluate() {
    if (!validate()) {
      throw new IllegalArgumentException("Invalid Expression");
    }

    Stack<Token> numbers = new Stack<>();

    for (Token token : tokens) {
      if (token.tokenType() == TokenType.NUMBER) {
        numbers.push(token);
        continue;
      }
      if (token.tokenType() == TokenType.OPERATOR) {

        if (numbers.size() < 2) {
          throw new IllegalArgumentException("Invalid Expression");
        }

        double n2 = Double.parseDouble(numbers.pop().value());
        double n1 = Double.parseDouble(numbers.pop().value());

        double value = evaluateOperation(n1, n2, token.value());

        numbers.push(new Token(TokenType.NUMBER, String.valueOf(value)));
      }
    }

    if (numbers.size() != 1) {
      throw new IllegalArgumentException("Invalid Expression");
    }

    return Double.parseDouble(numbers.pop().value());
  }

  private boolean validate() {
    int operands = 0;

    for (Token token : tokens) {
      switch (token.tokenType()) {
        case NUMBER -> operands++;

        case OPERATOR -> {
          if (operands < 2) {
            return false;
          }
          operands--;
        }

        default -> {
          return false;
        }
      }
    }

    return operands == 1;
  }

  private double evaluateOperation(double n1, double n2, String operator) {
    return switch (operator) {
      case "+" -> n1 + n2;
      case "-" -> n1 - n2;
      case "*" -> n1 * n2;
      case "/" -> {
        if (n2 == 0) {
          throw new IllegalArgumentException("Can't divide by zero");
        }
        yield n1 / n2;
      }
      case "^" -> Math.pow(n1, n2);
      default -> throw new IllegalArgumentException("Invalid operation");
    };
  }
}
