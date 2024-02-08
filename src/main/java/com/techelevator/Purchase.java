package com.techelevator;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private double currentBalance;


    public double feedMoney(String depositedString) {

        double depositedMoney = 0;

        do {

            try {
                depositedMoney = Double.parseDouble(depositedString);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid amount please make it a number in dollars.");
                 System.out.println("Please enter amount in dollars to add: ");
                 depositedString = userInput.nextLine();
                continue;
            }
            if(depositedMoney <= 0) {
                System.out.println("Money is less than 0 please enter valid amount.");
                 System.out.println("Please enter amount in dollars to add: ");
                 depositedString = userInput.nextLine();

            }
        }
        while (depositedMoney <= 0);

        currentBalance += depositedMoney;

        return currentBalance;
    }

    public void selectProduct() {
        System.out.println("Select money is running");

    }

    public void finishTransaction() {
        System.out.println("Finish transaction is running");
    }



}
