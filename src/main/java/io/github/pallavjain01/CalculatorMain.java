package io.github.pallavjain01;

import io.github.pallavjain01.gui.CalculatorFrame;

import javax.swing.*;

public class CalculatorMain {
  static void main() {
    SwingUtilities.invokeLater(CalculatorFrame::new);
  }
}
