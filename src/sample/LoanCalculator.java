package sample;

import java.text.NumberFormat;

public class LoanCalculator {
    private NumberFormat getCurrency = NumberFormat.getCurrencyInstance();
    private static final byte MONTHS_IN_YEAR = 12;
    private static final byte PERCENTAGE = 100;
    private static final byte WEEKS_IN_MONTH = 4;

    private int principal;
    private float annualInterest;
    private int years;

    // MMORTGAGE CALCULATOR
    // Calculate Repayments
    public Double mortgageCalculator(int principal, double annualInterest, int years){

        double monthlyInterest = annualInterest/ MONTHS_IN_YEAR / PERCENTAGE;
        int numberOfPayments = years * MONTHS_IN_YEAR;

        double monthlyRepayments = principal
                * monthlyInterest* (Math.pow(1+ monthlyInterest, numberOfPayments))
                / (Math.pow(1+ monthlyInterest,numberOfPayments)- 1);

        return monthlyRepayments;

    }
    // Calculate Remaining Balance
    public Double remainingBalance(int yearsElapsed, double repayments, int years){

        double paymentsLeft = (years - yearsElapsed) * MONTHS_IN_YEAR;
        double remainingBalance = (paymentsLeft * repayments);

        return  remainingBalance;
    }

    // Calculate Total Amount Paid

    public Double totalAmountPaid(double payments, int years){

        double totalAmountPaid = (payments * years * MONTHS_IN_YEAR);
        return totalAmountPaid;
    }

    // Calculate Interest

    public Double totalInterest (int principal, double payments, int years){

        double interest = (payments * (years * MONTHS_IN_YEAR)) - principal;
        return interest;
    }

    //SAVINGS CALCULATOR
    // Calculate

    // how much will i have a if i save X amount for Y Weeks,if i already have V AmountSaved
    public double calculateSavingsAmount(double principal, double weeklyIncome, double weeklyExpenses, int months){
        int numberOfWeeks = months * WEEKS_IN_MONTH ;

        double amount = principal + (weeklyIncome - weeklyExpenses) * (numberOfWeeks);
        return amount;

    }

    // how much (X amount) do i need to save a week (or month) to have Y Amount after V Months
    public double calculateLengthSaving(double desiredAmount, double principal, double weeklyIncome, double weeklyExpenses ){

        int savingsPerWeek = (int)(weeklyIncome - weeklyExpenses);
        double numberOfWeeks = (desiredAmount - principal)/ savingsPerWeek;

        return numberOfWeeks;
    }

    // INFLATION CALCULATOR
    // calculate inflation
}
