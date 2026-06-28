package io.github.pallavjain01.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CalculatorButton extends JButton {

  public CalculatorButton(String text) {

    super(text);

    setFocusable(false);

    setFont(new Font("SansSerif", Font.BOLD, 26));

    setForeground(Color.WHITE);

    setBackground(new Color(45,45,45));

    setBorder(new LineBorder(Color.WHITE));

    setPreferredSize(new Dimension(75,75));
  }

}