package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private VendingMachine vendingMachine = new VendingMachine();
    private Scanner userInput = new Scanner(System.in);
    private List<Item> items = vendingMachine.getItems();
    private Purchase purchase = new Purchase();
    public void run(){

        // prompt a user with main menu options:
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
                System.out.println("Is invalid command, please enter a valid command p");
                continue;
            }
             if(userInputNumber == 1){
                for(Item item : items) {
                    System.out.println(item.toString());
                }
                userInputNumber = 0;
            }
            else if(userInputNumber == 2){
                purchaseMenu();
                continue;
            } else if (userInputNumber == 3) {
                System.exit(1);
            }
            else {
                System.out.println("Is invalid command, please enter a valid command oo:");
            }
        }while((userInputNumber < 1) || userInputNumber > 3);
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
                System.out.println("Please enter amount in dollars to add: ");
                String depositedString = userInput.nextLine();
                purchase.feedMoney(depositedString);
                userInputNumber = 0;
            }
             else if(userInputNumber == 2){
                purchase.selectProduct(items);
                userInputNumber = 0;
            }
            else if (userInputNumber == 3) {
                purchase.finishTransaction();
                System.out.println();
               run();
            }
            else {
                System.out.println("Is invalid command, please enter a valid command :");
            }

        }while((userInputNumber < 1) || userInputNumber > 3);
    }
}
