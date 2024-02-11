package com.techelevator;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {

    public File createSalesReportFile(Purchase purchase) {
        File file = new File("Sales_Report_" + purchase.getCurrentDate() + "_" + purchase.getCurrentTime() + ".log");

        if(!file.exists()) {
            try{
                file.createNewFile();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            for(String key : purchase.getSalesReportMap().keySet()) {
                printWriter.println(key + "," + purchase.getSalesReportMap().get(key));
            }
            printWriter.println();
            printWriter.println();
            printWriter.println("TOTAL SALES $" + String.format("%.2f", purchase.getTotalSales()));
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    public Map<String, Integer> addSale(Item item, Map<String, Integer> map) {

        for(String key : map.keySet()) {

            if(key.equalsIgnoreCase(item.getName())) {
                int newQuantity = map.get(key);
                newQuantity++;
                map.put(key, newQuantity);
            }
        }
        return map;


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
