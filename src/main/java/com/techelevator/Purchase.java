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

    public Purchase() {
        VendingMachine vendingMachine = new VendingMachine();
        for(Item item : vendingMachine.getItems()) {
            salesReportMap.put(item.getName(), 0);
        }
//        for(Map.Entry<String, Integer> map : mapFile.entrySet()) {
//            System.out.println(map);
//        }
    }

    public Map<String, Integer> getSalesReportMap() {
        return salesReportMap;
    }

//    public Purchase(){
//        // create file object
//        String fileName = "Sales_Report_" + getCurrentDate() + "_" + getCurrentTime() + ".log";
//        file = new File(fileName);
//        if(!file.exists()){
//            try{
//                file.createNewFile();
//            }catch (IOException e){
//                System.out.println(e.getMessage());
//            }
//        } else if (file.isDirectory()) {
//            System.out.println("It's directory not a file.");
//        }
//        VendingMachine vendingMachine = new VendingMachine();
//        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
//            for(Item item : vendingMachine.getItems()) {
//                printWriter.println(item.getName() + ", "  + 0);
//            }
//            printWriter.println();
//            printWriter.println("TOTAL SALES: $0.00");
//        }
//        catch(FileNotFoundException e) {
//
//        }
//
//    }


    public double feedMoney(String depositedString) {

        int depositedMoney = 0;

        do {

            try {
                depositedMoney = Integer.parseInt(depositedString);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid amount please make it a number in dollars.");
                System.out.print("Please enter amount in dollars to add: ");
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

    public String selectProduct(List<Item> items, String slotNumber ) {
        String returnValue = "";

//        for(Item item : items) {
//            System.out.println(item.toString());
//        }
        boolean isItemExists = false;

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

        transaction.addTransaction(item.getName() + " " + item.getSlot(), item.getPrice(), currentBalance);
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
        transaction.addTransaction("GIVE CHANGE",currentBalance,0.0);
        currentBalance = 0;
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
