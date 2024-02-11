package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Item> items = new ArrayList<>();
    public List<Item> run(){
        //displaying all items in vendingMachine to user
        for(Item item : getItems()){
            System.out.println(item.toString());
        }
        return getItems();
    }



    public List<Item> getItems(){
        //adding item to list from vendingmachine.csv file
        File file = new File("vendingmachine.csv");
        if(!file.exists()){
            System.out.println(file.getName() + " doesn't exists.");
            System.exit(1);
        }
        else if(file.isDirectory()){
         System.out.println(file.getName() + " is directory not a file.");
         System.exit(1);
        }

        try(Scanner scan = new Scanner(file)){
            while(scan.hasNextLine()){
                String stringLine = scan.nextLine();
                String[] stringArray = stringLine.split(",");
                Item item = new Item();
                item.setSlot(stringArray[0]);
                item.setName(stringArray[1]);
                item.setPrice(stringArray[2]);
                item.setAnimal(stringArray[3]);

                items.add(item);
            }

        }catch (FileNotFoundException e){
            System.out.println("File not found.");
        }

      return items;
    }
}
