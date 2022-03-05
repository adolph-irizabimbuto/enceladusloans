package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class MortgageController extends LoginController {
    LoginController loginCont = new LoginController();
    LoanCalculator calculator = new LoanCalculator();
    NumberFormat getCurrency = NumberFormat.getCurrencyInstance();
    final int WEEKS_IN_MONTH = 4;
    final int FORTNIGHTS_IN_A_MONTH = 2;
    final int WEEKS_IN_FORTNIGHT = 2;

    // SAVINGS CALCULATOR
    @FXML
    public Pane savingsCalCulatorPane;
    @FXML
    private Label outPutLabel;
    @FXML
    private TextField principal;
    @FXML
    private TextField weeklyIncome;
    @FXML
    private TextField weeklyExpenes;
    @FXML
    private TextField lengthMonths;
    @FXML
    private Button homeNavButton;
    @FXML
    private ComboBox SavingsDropDown;

    @FXML
    private Button calculateLegnthButton;

    @FXML
    private TextField desiredAmount;


    //SPENDING VISUALIZER

    @FXML
    private PieChart pieChart;
    @FXML
    private Pane visualiserReportPane;
    @FXML
    private Button visualiseSpening;
    @FXML
    private Pane trackSpeningPane;
    @FXML
    private Label speningReportOutPutLabel;
    @FXML
    private TextField otherExpesnes;
    @FXML
    private TextField electricity;
    @FXML
    private TextField food;
    @FXML
    private TextField transportation;
    @FXML
    private TextField rent;
    @FXML
    private Button analyseSpeningButton;
    @FXML
    private Button backButton;


    //navBAR BUTTONS

    @FXML
    private Button calculateAmount;
    @FXML
    private Button mortgageNavButton;
    @FXML
    private Button InflationNavButton;


    //METHODS
    public void setSavingsDropDown() {
        ObservableList<String> options =
                FXCollections.observableArrayList("Hello", "hi");
        SavingsDropDown.setItems(options);


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("food", 10),
                new PieChart.Data("Car", 12),
                new PieChart.Data("Horse", 30)

        );
        pieChart.setData(pieChartData);
        pieChart.setStartAngle(90);

    }

    public void calculateSavings(javafx.event.ActionEvent event) {
        double startingBalance = Double.parseDouble(principal.getText());
        double weeklyPay = Double.parseDouble(weeklyIncome.getText());
        double weeklySpend = Double.parseDouble(weeklyExpenes.getText());
        int month = Integer.parseInt(lengthMonths.getText());

        if (event.getSource() == calculateAmount) {
            visualiserReportPane.toBack();
            String printAmount = getCurrency.format(calculator.calculateSavingsAmount
                    (startingBalance, weeklyPay, weeklySpend, month));
            outPutLabel.setText("Your Savings Account Balance after " + lengthMonths.getText() + " months is:  \n"
                    + printAmount);
        } else if (event.getSource() == calculateLegnthButton) {
            visualiserReportPane.toBack();
            double desiredAmountP = Double.parseDouble(desiredAmount.getText());
            int printLength = (int) (calculator
                    .calculateLengthSaving(desiredAmountP,
                            startingBalance, weeklyPay, weeklySpend));
            outPutLabel.setText("it will take you " + printLength + " weeks to save  " +
                    getCurrency.format(desiredAmountP));
        }
    }


    public void handleNAVBUTTONS(javafx.event.ActionEvent event) throws IOException {

        if (event.getSource() == homeNavButton) {
            Parent savingsPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();

        }
        if (event.getSource() == mortgageNavButton) {
            Parent savingsPane = FXMLLoader.load(getClass().getResource("mortgagePage.fxml"));
            Scene savingsPaneScene = new Scene(savingsPane);
            Stage savingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            savingsStage.setScene(savingsPaneScene);
            savingsStage.show();
        }
    }

    public void handleSpendingButtons(javafx.event.ActionEvent event) {
        if (event.getSource() == visualiseSpening) {
            trackSpeningPane.toFront();

        } else if (event.getSource() == analyseSpeningButton) {
            pieChart.toFront();
            visualiserReportPane.toFront();

            int monthlyRent = Integer.parseInt(rent.getText());
            int monthlyFood = Integer.parseInt((food.getText()));
            int monthlyElectricity = Integer.parseInt(electricity.getText());
            int monthlyTransport = Integer.parseInt(transportation.getText());
            int otherMonthlyEsxpenses = Integer.parseInt(otherExpesnes.getText());

            ObservableList<PieChart.Data> monthlySpendingReport =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Rent", monthlyRent),
                            new PieChart.Data("Food", monthlyFood),
                            new PieChart.Data("Electricity", monthlyElectricity),
                            new PieChart.Data("Transportation", monthlyTransport),
                            new PieChart.Data("Other Expenses", otherMonthlyEsxpenses));
            pieChart.setData(monthlySpendingReport);
            pieChart.setStartAngle(90);



            double totalFortnighitlyExpenses = monthlyElectricity + monthlyRent +
                    monthlyFood + monthlyTransport + otherMonthlyEsxpenses;
            double weeklyPay = Double.parseDouble(weeklyIncome.getText());


            if (totalFortnighitlyExpenses > weeklyPay * WEEKS_IN_FORTNIGHT) {

                weeklyExpenes.setText((Double.toString(totalFortnighitlyExpenses / WEEKS_IN_FORTNIGHT)));

                speningReportOutPutLabel.setText("Unfortunately you are unable to save :( \n - You're spending " +
                        getCurrency.format(totalFortnighitlyExpenses / WEEKS_IN_FORTNIGHT - weeklyPay) + " " +
                        "more than you earn a week.\n " +
                        "\n - try spending less on " + returnLargestExpense());
            } else if (totalFortnighitlyExpenses < weeklyPay * WEEKS_IN_FORTNIGHT) {
                double weeklySavings = (weeklyPay - (totalFortnighitlyExpenses / WEEKS_IN_FORTNIGHT));

                speningReportOutPutLabel.setText("With your current weekly Income of " +
                        getCurrency.format(weeklyPay) +
                        "\nyou should be able to comfortably save " +
                        getCurrency.format(weeklySavings) + " a week after \n" +
                        getCurrency.format(totalFortnighitlyExpenses / WEEKS_IN_FORTNIGHT) + " in weekly " +
                        "Expenses");
                weeklyExpenes.setText((Double.toString(totalFortnighitlyExpenses / WEEKS_IN_FORTNIGHT)));
            }

            visualiserReportPane.toFront();

        } else if (event.getSource() == backButton) {
            visualiserReportPane.toBack();
            trackSpeningPane.toBack();
            speningReportOutPutLabel.setText("");

        }


    }

    String returnLargestExpense() {
        double monthlyRent = Integer.parseInt(rent.getText());
        double monthlyFood = Integer.parseInt((food.getText()));
        double monthlyElectricity = Integer.parseInt(electricity.getText());
        double monthlyTransport = Integer.parseInt(transportation.getText());
        double otherMonthlyEsxpenses = Integer.parseInt(otherExpesnes.getText());

        HashMap<String, Double> expenses = new HashMap<String, Double>();

        expenses.put("Electricity" , monthlyElectricity);
        expenses.put("Food", monthlyFood);
        expenses.put("Rent",monthlyRent);
        expenses.put("Transport", monthlyTransport);
        expenses.put("Other Expenses", otherMonthlyEsxpenses);

        String maxKey = "Electricity";

        for(String i: expenses.keySet()){
            if(expenses.get(i) > expenses.get(maxKey)){
                maxKey = i;
            }
        }

        return maxKey;
    }

}
