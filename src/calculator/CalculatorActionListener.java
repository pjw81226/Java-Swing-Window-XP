package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorActionListener implements ActionListener {
    private final Calculator calculator;

    public CalculatorActionListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputOperator = ((JButton) e.getSource()).getText();
        try {
            int num = Integer.parseInt(inputOperator);
            calculator.pushNumberButton(num);
        } catch (NumberFormatException numException) {
            switch (inputOperator) {
                case "CE" -> calculator.buttonCE();
                case "C" -> calculator.buttonC();
                case "." -> calculator.setBelowZero(true);
                case "+", "-", "*", "/", "=" -> calculator.buttonOperator(inputOperator);
                case "Back" -> calculator.buttonBack();
                case "+/-" -> calculator.buttonSign();
            }
        }
    }
}
