package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {



    public void addSale(Item item, Map<String, Integer> map) {

        for(String key : map.keySet()) {

            if(key.equalsIgnoreCase(item.getName())) {
                int newQuantity = map.get(key);
                newQuantity++;
                map.put(key, newQuantity);
            }
        }
//        for(Map.Entry<String, Integer> maps : map.entrySet()) {
//            System.out.println(maps);
//        }

//        try(Scanner scanner = new Scanner(file) ; PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, false))) {
//            while(scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] arrayString = line.split(", ");
//                int quantity = Integer.parseInt(arrayString[1]);
//                if(line.contains(item.getName())) {
//                    line = line.replace(arrayString[1], quantity++ + "");
//                }
//                printWriter.println("line");
//            }
//        }
//        catch(FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
