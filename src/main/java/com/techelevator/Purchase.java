package com.techelevator;

import java.io.*;
import java.lang.invoke.VarHandle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private Transaction transaction = new Transaction();
    private double currentBalance;
    private File file;

    public Purchase(){
        // create file object
        String fileName = "Sales_Report_" + getCurrentDate() + "_" + getCurrentTime() + ".log";
        file = new File(fileName);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else if (file.isDirectory()) {
            System.out.println("It's directory not a file.");
        }
        VendingMachine vendingMachine = new VendingMachine();
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            for(Item item : vendingMachine.getItems()) {
                printWriter.println(item.getName() + ", "  + 0);
            }
        }
        catch(FileNotFoundException e) {

        }

    }


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


        transaction.addTransaction("FEED MONEY",depositedMoney,currentBalance);
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

       transaction.addTransaction(item.getName() + " " + item.getSlot(), item.getPrice(), currentBalance);

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

        transaction.addTransaction("GIVE CHANGE",currentBalance,0.0);

        currentBalance = 0;

    }

    public double getCurrentBalance() {
        return currentBalance;
    }


    public String getCurrentDate(){

        String pattern = "MM-dd-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    public String getCurrentTime(){
        String pattern = "hh-mm-ss-a";
        SimpleDateFormat timeFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String currentTime = timeFormat.format(date);

        return currentTime;
    }
}
