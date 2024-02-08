package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private double currentBalance;


    public void feedMoney() {

        double depositedMoney = 0;

        do {
            System.out.println("Please enter amount in dollars to add: ");
            String depositedString = userInput.nextLine();
            try {
                depositedMoney = Double.parseDouble(depositedString);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid amount please make it a number in dollars.");
                continue;
            }
            if(depositedMoney <= 0) {
                System.out.println("Money is less than 0 please enter valid amount.");
            }
        }
        while (depositedMoney <= 0);

        currentBalance += depositedMoney;
    }

    public void selectProduct() {
        System.out.println("Select money is running");

    }

    public void finishTransaction() {
        System.out.println("Finish transaction is running");
    }



}
