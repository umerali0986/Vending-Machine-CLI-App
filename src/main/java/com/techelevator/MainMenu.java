package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private VendingMachine vendingMachine = new VendingMachine();
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = vendingMachine.getItems();
    private Purchase purchase;
    private SalesReport salesReport = new SalesReport();


    public MainMenu(){
        purchase = new Purchase();
    }


    public void run(){

        System.out.println("******** WELCOME TO VENDING MACHINE ********");

        int userInputNumber = 0;
        do{
            System.out.println();
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println();
            System.out.print("Please enter an option? ");
            String userInputString = userInput.nextLine();

            try {
                userInputNumber = Integer.parseInt(userInputString);
            }catch (NumberFormatException e){
                System.out.println("Is invalid command, please enter a valid command");
                continue;
            }
             if(userInputNumber == 1){
                 //displaying all current items in vendingMachine to user;
                for(Item item : items) {
                    System.out.println(item.toString());
                }
                userInputNumber = 0;
            }
            else if(userInputNumber == 2){
                purchaseMenu();
                continue;
            }
            else if (userInputNumber == 3) {
                salesReport.createSalesReportFile(purchase);
                System.exit(1);
            }
            else if(userInputNumber == 4) {
                //Displaying sales report information from salesReportMap to user
                Map<String, Integer> salesMap = purchase.getSalesReportMap();
                for(String key : salesMap.keySet()) {
                    System.out.println(key + "," + salesMap.get(key));
                }
                System.out.println();
                System.out.println("Total Sales $" + String.format("%.2f", purchase.getTotalSales()));
                userInputNumber = 0;
             }
            else {
                System.out.println("Is invalid command, please enter a valid command");
            }
        }while((userInputNumber < 1) || userInputNumber > 4);
    }


    public void purchaseMenu(){

        int userInputNumber = 0;

        do{
            System.out.println();
            System.out.println("Current Money Provided: $" + String.format("%.2f", purchase.getCurrentBalance()));
            System.out.println();
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println();
            System.out.print("Please enter an option? ");
            String userInputString = userInput.nextLine();
            try {
                userInputNumber = Integer.parseInt(userInputString);
            }catch (NumberFormatException e){
                System.out.println("Is invalid command, please enter a valid command :");
                continue;
            }
            if(userInputNumber == 1){
                //calling feedMoney() to take user money and add to currentBalance
                System.out.print("Please enter amount in whole numbers to add: $");
                String depositedString = userInput.nextLine();
                purchase.feedMoney(depositedString);
                userInputNumber = 0;
            }
             else if(userInputNumber == 2){
                 //displays all items in vendingMachine to user
                for(Item item : items) {
                    System.out.println(item.toString());
                }
                 System.out.println();
                 System.out.print("Please enter slot number: ");
                 String slotNumber = userInput.nextLine();
                 //calls selectProduct() to dispense item if item exists
                 purchase.selectProduct(items, slotNumber);
                userInputNumber = 0;
            }
            else if (userInputNumber == 3) {
                //calls finishTransaction() to return currentBalance in coins to user
                purchase.finishTransaction(purchase.getCurrentBalance());
                System.out.println();
                run();
            }
            else {
                System.out.println("Is invalid command, please enter a valid command :");
            }

        }
        while((userInputNumber < 1) || userInputNumber > 3);
    }

}
