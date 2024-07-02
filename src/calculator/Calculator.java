package calculator;

import component.TitleBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Calculator extends JPanel {
    // Status
    private static final int PREV_PUSH_NUMBER = 0;
    private static final int PREV_PUSH_OPERATOR = 1;
    private static final int PREV_PUSH_EQUAL = 2;

    private final JTextField textField;
    private boolean isBelowZero;
    private double number, result;
    private char preOperator;
    private int checkStatus;

    public Calculator() {
        setLayout(new BorderLayout());
        setBackground(new Color(192, 192, 192));

        try{ UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); }
        catch(Exception e){ }

        TitleBar titleBar = new TitleBar("Calculator", this::closeCalculator);
        add(titleBar, BorderLayout.NORTH);

        textField = new JTextField("0");
        textField.setPreferredSize(new Dimension(100, 50));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEnabled(false);
        textField.setForeground(Color.BLACK);
        add(textField, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        JButton[] calcButtons = new JButton[20];
        String[] buttonLabels = {
                "Back", "CE", "C", "=",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "+/-", ".", "+"
        };
        Dimension buttonSize = new Dimension(40, 20);
        ActionListener listener = new CalculatorActionListener(this);
        for (int i = 0; i < 20; i++){
            calcButtons[i] = new JButton(buttonLabels[i]);
            calcButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            calcButtons[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            calcButtons[i].addActionListener(listener);
            calcButtons[i].setPreferredSize(buttonSize);
            panel.add(calcButtons[i]);
        }
        add(panel, BorderLayout.SOUTH);

        buttonC();
    }

    public void pushNumberButton(int pushNumber){
        if (checkStatus == PREV_PUSH_OPERATOR) buttonCE();
        if (checkStatus == PREV_PUSH_EQUAL) buttonC();

        String prevString = textField.getText();
        if (isBelowZero){
            textField.setText(prevString + pushNumber);
        }
        else{
            if (prevString.equals("0")){
                textField.setText(String.valueOf(pushNumber));
            }
            else{
                textField.setText(prevString + pushNumber);
            }
        }
        checkStatus = PREV_PUSH_NUMBER;
    }
    public void buttonOperator(String operator){
        char pushOperator = operator.charAt(0);
        number = Double.parseDouble(textField.getText());
        switch (preOperator) {
            case '+' -> result += number;
            case '-' -> result -= number;
            case '*' -> result *= number;
            case '/' -> result /= number;
            default -> result = number;
        }
        textField.setText(String.valueOf(result));
        preOperator = pushOperator;
        if (pushOperator == '=')
            checkStatus = PREV_PUSH_EQUAL;
        else
            checkStatus = PREV_PUSH_OPERATOR;
    }
    public void buttonC(){
        isBelowZero = false;
        number = 0;
        result = 0;
        preOperator = ' ';
        textField.setText("0");
        checkStatus = PREV_PUSH_NUMBER;
    }
    public void buttonCE(){
        textField.setText("0");
        isBelowZero = false;
    }
    public void buttonBack(){
        if (checkStatus == PREV_PUSH_NUMBER){
            String resultText = textField.getText();
            if (resultText.length() > 1){
                textField.setText(resultText.substring(0, resultText.length() - 1));
            } else{
                textField.setText("0");
            }
        }
    }
    public void buttonSign(){
        if (checkStatus == PREV_PUSH_NUMBER){
            String resultText = textField.getText();
            textField.setText(resultText.startsWith("-") ? resultText.substring(1) : "-" + resultText);
        }
    }
    public void setBelowZero(boolean isBelowZero){
        this.isBelowZero = isBelowZero;
    }
    private void closeCalculator(){
        this.setVisible(false);
    }
}
