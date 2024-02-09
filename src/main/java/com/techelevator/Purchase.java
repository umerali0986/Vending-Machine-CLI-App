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

    public void selectProduct(List<Item> items) {

        for(Item item : items) {
            System.out.println(item.toString());
        }
        System.out.println();
        System.out.println("Please enter slot number: ");
        String slotNumber = userInput.nextLine();
        boolean isItemExists = false;

        for(Item item : items) {
            if(item.getSlot().equalsIgnoreCase(slotNumber)) {
                isItemExists = true;
                if(item.isSoldOut()) {
                    System.out.println(item.getName() + " is sold out.");
                    break;
                }
                else {
                    System.out.println(dispenseItem(item));
                    break;
                }
            }
        }
        if(!isItemExists) {
            System.out.println("Invalid slot code. ");
        }


    }

    public String dispenseItem(Item item) {
        if(getCurrentBalance() < item.getPrice()) {
            return "Insufficient funds.";
        }
        currentBalance -= item.getPrice();
        item.setQuantity(item.getQuantity() - 1);
        String message = item.getName() + " | $" + String.format("%.2f", item.getPrice()) + "| $" + String.format("%.2f", currentBalance);
//        System.out.println();

        return message + " | " + item.getAnimal().makeASound();
    }

    public void finishTransaction() {
        double[] changeAmounts = {0.25, 0.10, 0.05, 0.01};
        int[] amountCount = new int[changeAmounts.length];
        double changeRequired = currentBalance;
        for (int i = 0; i < changeAmounts.length; i++) {
            while (changeRequired >= changeAmounts[i]) {
                changeRequired -= changeAmounts[i];
                amountCount[i]++;
            }
        }
        System.out.println();
        for (int i = 0; i < changeAmounts.length; i++) {
            if (amountCount[i] > 0) {
                System.out.println(amountCount[i] + " $" + (String.format("%.2f", changeAmounts[i])));
            }

        }
        currentBalance = 0;
    }

    public double getCurrentBalance() {
        // TODO format decimal
        return currentBalance;
    }



}
