package io.github.pallavjain01.core;

import java.util.ArrayList;

public class Calculator {
  private static final Tokenizer tokenizer = new Tokenizer("");
  private static final ExpressionEvaluator evaluator = new ExpressionEvaluator(new ArrayList<>());

  public static double evaluate(String expression) {
    tokenizer.setExpression(expression);
    evaluator.setTokens(
        NotationEvaluator.infixToPostfix(
            tokenizer.tokenize()
        )
    );
    return evaluator.evaluate();
  }
}
