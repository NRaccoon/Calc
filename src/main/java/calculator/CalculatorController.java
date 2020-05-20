package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private float number1;
    private String operator = "";
    private boolean tort = false;
    private int x = 0;

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
            if (operator.isEmpty()) {
                return;
            }
            float number2 = Float.parseFloat(display.getText());
            float result = (float) calculator.calculate(number1, number2, operator);
            if (tort) {
                String[] Str1 = String.valueOf(number1).split("[,]", 2);
                String[] Str2 = String.valueOf(number2).split("[,]", 2);

                x = Math.max(Str1[1].length(), Str2[1].length());
                display.setText(String.format("%," + x + "f", result));
            } else {
                x = 0;
                display.setText(String.format(((int) result == result) ? "%.0f" : "%s", result));
            }
            operator = "";
        } else if (operatorPressed.equals("AC")) {
            display.setText("0");
            startNumber = false;
            tort = false;
            operator = "";
        } else if (operatorPressed.equals("+/-")) {
            if (display.getText().startsWith("-")) {
                display.setText(display.getText().substring(1));
            } else {
                display.setText("-" + display.getText());
            }
        } else if (operatorPressed.equals(".")) {
            tort = true;
            display.setText(display.getText().concat("."));
        } else {
            if (!operator.isEmpty()) {
                return;
            }
            number1 = Float.parseFloat(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }

    }

}
