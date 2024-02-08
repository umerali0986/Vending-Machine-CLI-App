package com.techelevator;

import java.util.Scanner;

public class MainMenu {

    private Scanner userInput = new Scanner(System.in);
    public void run(){


        // prompt a user with main menu options:
        System.out.println("******** WELCOME TO VENDING MACHINE ********");
        System.out.println();
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        System.out.println();

        int userInputNumber = 0;
        do{
            System.out.print("Please enter an option? ");
            String userInputString = userInput.nextLine();
            try {
                userInputNumber = Integer.parseInt(userInputString);
            }catch (NumberFormatException e){
                System.out.println("Is invalid command, please enter a valid command");
                continue;
            }
             if(userInputNumber == 1){
                // TODO- create vending machine class
                VendingMachine vendingMachine = new VendingMachine();
                vendingMachine.run();
//                System.out.println("Vending machine runs");
            }
            else if(userInputNumber == 2){
                purchaseMenu();
                continue;
            } else if (userInputNumber == 3) {
                System.exit(1);
            }
            if((userInputNumber < 1) || userInputNumber > 3){
                System.out.println("Is invalid command, please enter a valid command :");
            }
        }while((userInputNumber < 1) || userInputNumber > 3);
    }


    public void purchaseMenu(){
       Purchase purchase = new Purchase();

        //System.out.println("Current Money Provided: $" + purchase.getCurrentBalance());
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println();

        int userInputNumber = 0;

        do{
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
                purchase.selectProduct();
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
