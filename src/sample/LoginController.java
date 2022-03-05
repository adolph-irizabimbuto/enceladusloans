package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Pane loginPanel;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button logInButton;

    @FXML
    private CheckBox reCaptchaCheckBox;

    @FXML
    private Label reCaptcaPrompt;

    @FXML
    private TextField reCaptchaTextField;

    @FXML
    private Label loginBanner;

    @FXML
    private Button reCaptchaSubmitButton;

    @FXML
    private Label loginError;
    @FXML
    private Pane reCaptchaPane;

    @FXML
    private Button recaptchaSkip;
    @FXML
    public Pane homePane;
    @FXML
    private Pane recCaptchaScreenDoor;

    @FXML
    private Pane buttonsScrenndoor;

    // homeButtons
    @FXML
    private Button homeMortgageButton;

    @FXML
    private Button homeInflationButton;

    @FXML
    private Button homeSavingsButton;

    // homePanes
    @FXML
    private AnchorPane rootPane;
    @FXML
    public Pane mortgageCalculatorPane;


    // MortgaggePage

    @FXML
    private Label outPutLabel;

    @FXML
    private TextField loanAmountText;

    @FXML
    private TextField YerasField;

    @FXML
    private TextField annualInterestField;

    @FXML
    private TextField yearsElapsedField;

    @FXML
    private Button calculateMonthlyRepayments;

    @FXML
    private Button calculateTotalAmountPaid;

    @FXML
    private Button calculateTotalInterest;

    @FXML
    private Button calculateBalance;

    // NAVBUTTONS

    @FXML
    private Button savingsNavButton;

    @FXML
    private Button InflationNavButton;

    @FXML
    private Button homeNavButton;


    LoanCalculator calculator = new LoanCalculator();
    NumberFormat getCurrency = NumberFormat.getCurrencyInstance();
    setStage setStage = new setStage();


    // METHODS
    // handleLogin
    //
    public void handleLogin() {
        String storedUsername = "aries";
        String storedPassword = "aries";
        String usernameEntered = usernameText.getText().trim();
        String passwordEntered = passwordText.getText().trim();
        if (usernameEntered.equals(storedUsername) & passwordEntered.equals(storedPassword)) {
            try {
                reCaptchaPane.toFront();
                loginError.setText("Please authenticate prior to Login");
                loginError.setTextFill(Color.web("#008000"));
            } catch (Exception e) {

            }
        }
        else if(usernameEntered.equals("") || passwordEntered.equals("")){
            loginError.setText("you are actually required to enter \n something you idiot");
        }
        else if (!usernameEntered.equals(storedUsername)) {
            loginError.setText("Incorrect username, please try again.");
            recCaptchaScreenDoor.toFront();
        } else if (!passwordEntered.equals(storedPassword)) {
            recCaptchaScreenDoor.toFront();
            loginError.setText("Incorrect password, please try again.");
        }

    }

    // handleRecptcha
    public void handleReCaptcha() {
        String stringSource = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        int stringLength = 8;
        for (int i = 0; i < stringLength; i++) {
            int index = (int) (stringSource.length() * Math.random());
            sb.append(stringSource.charAt(index));
        }
        String generatedString = sb.toString();

        if (reCaptchaCheckBox.isSelected()) {
            reCaptchaPane.toFront();
            reCaptcaPrompt.toFront();
            reCaptcaPrompt.setText(generatedString);
            reCaptchaTextField.toFront();
            reCaptchaSubmitButton.toFront();
            recaptchaSkip.toFront();
            buttonsScrenndoor.toBack();

        }

    }

    // handleRecaptcaButtons
    public void handleReCaptchaButtons(javafx.event.ActionEvent event) throws IOException {
        String enteredString = reCaptchaTextField.getText();
        String generatedString = reCaptcaPrompt.getText();

        if (event.getSource() == reCaptchaSubmitButton) {
            if (enteredString.equals(generatedString)) {
                Parent savingsPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                Scene savingsPaneScene = new Scene(savingsPane);
                Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                savingsStage.setScene(savingsPaneScene);
                savingsStage.show();

            } else if (!enteredString.equals(generatedString)) {
                loginError.setText("Incorrect, please try again," +
                        "\n or press skip to generate another code");
                loginError.setTextFill(Color.web("#FF0000"));
            }

        } else if (event.getSource() == recaptchaSkip) {
            reCaptcaPrompt.setText(generatedString);
            reCaptchaTextField.setText("");
        }

    }

    // Handle home Buttons
    public void handleHomeButtons(javafx.event.ActionEvent event) throws IOException {
        if (event.getSource() == homeMortgageButton) {
            Parent savingsPane = FXMLLoader.load(getClass().getResource("mortgagePage.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();
        }
        if (event.getSource() == homeSavingsButton){
            Parent savingsPane = FXMLLoader.load(getClass().getResource("savings.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();
        }

    }

    // HandleCalculations

    public void handleMortgageCalculations(javafx.event.ActionEvent event) {
        int Principal = Integer.parseInt(loanAmountText.getText());
        double interestRate = Double.parseDouble(annualInterestField.getText());
        int years = Integer.parseInt(YerasField.getText());

        Double payments = calculator.mortgageCalculator(Principal, interestRate, years);
        ;

        if (event.getSource() == calculateMonthlyRepayments) {
            outPutLabel.setText("Your monthly repayment is: " + getCurrency.format(payments));

        } else if (event.getSource() == calculateBalance) {
            int yearsElapsed = Integer.parseInt(yearsElapsedField.getText());
            try {
                double balance = calculator.remainingBalance(yearsElapsed, payments, years);
                outPutLabel.setText("Your remaining balance after " + yearsElapsed +
                        " years is: " + getCurrency.format(balance));
            } catch (Exception e) {
//                make a specific try/catch block
                System.out.println("a mistake somehwere");

            }

        } else if (event.getSource() == calculateTotalAmountPaid) {
            if (years > 0 && years <= 30) {
                double totalAmountPaid = calculator.totalAmountPaid(payments, years);
                outPutLabel.setText("Total Amount Paid (Over the life of a " +
                        YerasField.getText() + " year loan is):" +
                        "\n" + getCurrency.format(totalAmountPaid));
            } else {
                outPutLabel.setText("Please enter a value between 1 and 30");
                YerasField.setText("");
                YerasField.setPromptText("enter value between 1 and 30");
            }
        } else if (event.getSource() == calculateTotalInterest) {
            double interestPaid = calculator.totalInterest(Principal, payments, years);
            outPutLabel.setText("Total Interest Paid is:  " + getCurrency.format(interestPaid));

        }
    }

    public void handleNAVBUTTONS(javafx.event.ActionEvent event) throws IOException {

        MortgageController mc = new MortgageController();
        
        if (event.getSource() == homeNavButton) {
            Parent savingsPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();
        }
        if (event.getSource() == savingsNavButton) {
            Parent savingsPane = FXMLLoader.load(getClass().getResource("savings.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleDataValidation(double value, int min, int max) {


        try {
            if (value > min && value < max) {
                outPutLabel.setText("please enter a value between " + min + " and " + max);

            }
        } catch (InputMismatchException e) {
            outPutLabel.setText("please enter a ");
        } catch (Exception e) {
            outPutLabel.setText("please refer to teh above");
        }
    }


}
