package com.techelevator;

import java.io.*;
import java.lang.invoke.VarHandle;
import java.text.SimpleDateFormat;
import java.util.*;

public class Purchase {
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private Transaction transaction = new Transaction();
    private double currentBalance;
    //private File file;
    private Map<String, Integer> salesReportMap = new HashMap<>();
    private SalesReport salesReport = new SalesReport();
    private double totalSales = 0.00;
    private File file;


    public Purchase() {
        //adding items to salesReportMap
        VendingMachine vendingMachine = new VendingMachine();
        for(Item item : vendingMachine.getItems()) {
            salesReportMap.put(item.getName(), 0);
        }
        file = new File("Vending.log");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else if (file.isDirectory()) {
            System.out.println("It's directory not a file.");
        }
//
    }

    public Map<String, Integer> getSalesReportMap() {
        return salesReportMap;
    }

//


    public double feedMoney(String depositedString) {

        int depositedMoney = 0;

        do {
            try {
                depositedMoney = Integer.parseInt(depositedString);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input please make it a whole number in dollars.");
                System.out.println();
                System.out.print("Please enter amount in whole numbers to add: $");

                depositedString = userInput.nextLine();
                continue;
            }
            if(depositedMoney == 0) {
                System.out.println("0 is not a valid amount please enter a positive whole number: $");
                System.out.println();
                System.out.print("Please enter amount in dollars to add: $");
                depositedString = userInput.nextLine();
            }
            if(depositedMoney < 0) {
                System.out.println("Money is less than 0 please enter a positive amount in whole numbers.");
                System.out.println();
                System.out.print("Please enter amount in dollars to add: $");
                 depositedString = userInput.nextLine();

            }
        }
        while (depositedMoney <= 0);

        currentBalance += depositedMoney;

        //calling addtransaction() to add transaction to the Vending.log file
        transaction.addTransaction(file, "FEED MONEY",depositedMoney,currentBalance);
        return currentBalance;
    }

    public String selectProduct(List<Item> items, String slotNumber ) {
        String returnValue = "";
        boolean isItemExists = false;
        //looping through item List to check if item matches slotNumber or is sold out
        //dispenses item if item exists or is not sold out
        for(Item item : items) {
            if(item.getSlot().equalsIgnoreCase(slotNumber)) {
                isItemExists = true;
                if(item.isSoldOut()) {
                    System.out.println(item.getName() + " is sold out.");
                    returnValue = item.getName() + " is sold out.";
                    break;
                }
                else {
                    returnValue = dispenseItem(item);
                    System.out.println(returnValue);
                    break;
                }
            }
        }
        if(!isItemExists) {
            System.out.println("Invalid slot code.");
            returnValue = "Invalid slot code.";
        }
        return returnValue;
    }

    public String dispenseItem(Item item) {
        if(getCurrentBalance() < item.getPrice()) {
            return "Insufficient funds.";
        }
        currentBalance -= item.getPrice();
        item.setQuantity(item.getQuantity() - 1);
        String message = item.getName() + " | $" + String.format("%.2f", item.getPrice()) + " | $" + String.format("%.2f", currentBalance);

        transaction.addTransaction(file, item.getName() + " " + item.getSlot(), item.getPrice(), currentBalance);
        salesReport.addSale(item, salesReportMap);
        totalSales += item.getPrice();

        return message + " | " + item.getAnimal().makeASound();
    }

    public String finishTransaction(double currentBalance) {
        double[] changeAmounts = {0.25, 0.10, 0.05, 0.01};
        int[] amountCount = new int[changeAmounts.length];
        double changeRequired = currentBalance;
        String returnValue = "";
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
                returnValue +=  amountCount[i] + " $" + String.format("%.2f", changeAmounts[i]);
            }
        }
        //calling addtransaction to add transaction to the Vending.log file
        transaction.addTransaction(file, "GIVE CHANGE",currentBalance,0.0);
        setCurrentBalance(0);
        return  returnValue;
    }
    public double getTotalSales() {
        return totalSales;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
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
