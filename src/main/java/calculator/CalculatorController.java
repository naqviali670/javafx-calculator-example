package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class CalculatorController {

    @FXML
    private Button PlusMnusButton;
    @FXML
    private Button DotBUtton;
    @FXML
    private Button ACButton;
    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

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
            if(digitPressed.equals(".")){
                if(!display.getText().contains(".")){
                    display.setText(display.getText()+".");
                }
            }
            else if(digitPressed.equals("\u00B1")){
                if(display.getText().charAt(0)=='-'){
                    display.setText(display.getText().substring(1));
                }else{
                    display.setText("-"+display.getText());
                }
            }
            else{
            display.setText(display.getText() + digitPressed);}
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
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
            DecimalFormat df = new DecimalFormat("###.##");
           display.setText(df.format(result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    private void ACAction(ActionEvent actionEvent) {
         display.clear();
        startNumber = true;
 operator = "";
    }

    @FXML
    private void DotAction(ActionEvent actionEvent) {
        if(!display.getText().contains(".")){
           display.setText(display.getText()+".");
        }
    }

    @FXML
    public void plusMinusAction(ActionEvent actionEvent) {
    }
}
