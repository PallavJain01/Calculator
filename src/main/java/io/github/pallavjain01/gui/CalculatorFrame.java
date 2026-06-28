package io.github.pallavjain01.gui;

import javax.swing.*;

public class CalculatorFrame extends JFrame {

  public CalculatorFrame() {
    super("Calculator");

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(new CalculatorPanel());

    pack();
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}