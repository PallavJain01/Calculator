package io.github.pallavjain01.data;

public record Token(
    TokenType tokenType,
    String value
) {
  @Override
  public String toString() {
    return value;
  }
}
