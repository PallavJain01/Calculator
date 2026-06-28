package io.github.pallavjain01.gui;

import io.github.pallavjain01.core.Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorPanel extends JPanel {

  private final JLabel expressionLabel;
  private final JLabel resultLabel;

  private final StringBuilder expression = new StringBuilder();

  public CalculatorPanel() {

    setLayout(new BorderLayout(10,10));
    setBackground(new Color(30,30,30));
    setBorder(new EmptyBorder(10,10,10,10));

    JPanel display = new JPanel();

    display.setLayout(new GridLayout(2,1));
    display.setBackground(new Color(40,40,40));
    display.setBorder(new EmptyBorder(10,10,10,10));

    expressionLabel = new JLabel("", SwingConstants.RIGHT);
    expressionLabel.setForeground(Color.LIGHT_GRAY);
    expressionLabel.setFont(new Font("SansSerif", Font.PLAIN,20));

    resultLabel = new JLabel(" ", SwingConstants.RIGHT);
    resultLabel.setForeground(Color.WHITE);
    resultLabel.setFont(new Font("SansSerif", Font.BOLD,36));

    display.add(expressionLabel);
    display.add(resultLabel);

    add(display, BorderLayout.NORTH);

    JPanel buttons = new JPanel(new GridBagLayout());

    buttons.setBackground(getBackground());

    add(buttons, BorderLayout.CENTER);

    addButton(buttons,"(",0,1,1,1);
    addButton(buttons,")",1,1,1,1);
    addButton(buttons,"^",2,0,1,1);
    addButton(buttons,"i",3,0,1,1);

    addButton(buttons,"CLR",0,0,1,1);
    addButton(buttons,"DEL",1,0,1,1);
    addButton(buttons,"/",2,1,1,1);
    addButton(buttons,"*",3,1,1,1);

    addButton(buttons,"7",0,2,1,1);
    addButton(buttons,"8",1,2,1,1);
    addButton(buttons,"9",2,2,1,1);
    addButton(buttons,"-",3,2,1,1);

    addButton(buttons,"4",0,3,1,1);
    addButton(buttons,"5",1,3,1,1);
    addButton(buttons,"6",2,3,1,1);
    addButton(buttons,"+",3,3,1,1);

    addButton(buttons,"1",0,4,1,1);
    addButton(buttons,"2",1,4,1,1);
    addButton(buttons,"3",2,4,1,1);
    addButton(buttons,"=",3,4,1,2);

    addButton(buttons,"0",0,5,2,1);
    addButton(buttons,".",2,5,1,1);
  }

  private void addButton(
      JPanel panel,
      String text,
      int x,
      int y,
      int width,
      int height
  ){

    CalculatorButton button = new CalculatorButton(text);

    button.addActionListener(e -> onButtonPressed(text));

    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    gridBagConstraints.gridx = x;
    gridBagConstraints.gridy = y;

    gridBagConstraints.gridwidth = width;
    gridBagConstraints.gridheight = height;

    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;

    gridBagConstraints.insets = new Insets(6,6,6,6);

    gridBagConstraints.fill = GridBagConstraints.BOTH;

    panel.add(button, gridBagConstraints);
  }

  private void onButtonPressed(String text){

    switch (text){

      case "CLR" -> {
        expression.setLength(0);
        resultLabel.setText(" ");
      }

      case "DEL" -> {
        if (expression.length() == 1) {
          expression.setCharAt(0, ' ');
        } else if(!expression.isEmpty()){
          expression.deleteCharAt(expression.length()-1);
        }
      }

      case "=" -> {
        try {
          double answer = Calculator.evaluate(expression.toString());
          resultLabel.setText("= " + answer);
        } catch (Exception ex) {
          resultLabel.setText("Error");
        }
      }

      case "i" -> JOptionPane.showMessageDialog(
          this,
          """
          Simple Calculator
          by Pallav Jain
          
          Supported operators:
          +  -  *  /  ^
          Parentheses
          
          Version 1.0
          """
      );

      default -> expression.append(text);
    }

    expressionLabel.setText(expression.toString());
  }

}