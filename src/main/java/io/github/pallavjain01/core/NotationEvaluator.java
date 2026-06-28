package io.github.pallavjain01.core;

import io.github.pallavjain01.data.Token;
import io.github.pallavjain01.data.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NotationEvaluator {
  public static List<Token> infixToPostfix(List<Token> infixTokens) {
    List<Token> output = new ArrayList<>();
    Stack<Token> operators = new Stack<>();

    for (Token token: infixTokens) {
      switch (token.tokenType()) {
        case NUMBER -> {
          output.add(token);
        }

        case OPERATOR -> {
          while (!operators.empty() &&
                  operators.peek().tokenType() == TokenType.OPERATOR &&
                  shouldPop(operators.peek(), token)
          ) {
            output.add(operators.pop());
          }
          operators.push(token);
        }

        case LEFT_PAREN -> {
          operators.push(token);
        }

        case RIGHT_PAREN -> {
          while (!operators.isEmpty() &&
              operators.peek().tokenType() != TokenType.LEFT_PAREN
          ) {
            output.add(operators.pop());
          }
          if (operators.isEmpty()) {
            throw new IllegalArgumentException(
                "MisMatched parenthesis"
            );
          }
          operators.pop();
        }
      }
    }

    while (!operators.isEmpty()) {

      Token token = operators.pop();

      if (token.tokenType() == TokenType.LEFT_PAREN) {

        throw new IllegalArgumentException(
            "Mismatched parentheses"
        );
      }

      output.add(token);
    }

    return output;
  }

  private static boolean shouldPop(Token stackOperator, Token currentOperator) {
    int stackPrecedence =  precedence(stackOperator.value());

    int currentPrecedence =  precedence(currentOperator.value());

    if (currentOperator.value().equals("^")) {
      return stackPrecedence > currentPrecedence;
    }

    return stackPrecedence >= currentPrecedence;
  }

  private static int precedence(String operator) {
    return switch (operator) {
      case "+", "-" -> 1;
      case "*", "/" -> 2;
      case "^" -> 3;
      default -> -1;
    };
  }
}
