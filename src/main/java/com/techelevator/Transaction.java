package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private File file;

    public File addTransaction(String transactionName, double deposit, double currentBalance){

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

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(file,true))){

            writer.println(getCurrentDate() + " " + getCurrentTime() + " " + transactionName +
                    ((transactionName.equals("FEED MONEY") || transactionName.equals("GIVE CHANGE")) ?  ": " : " ")
                    + "$" + String.format("%.2f",deposit) + " $" + String.format("%.2f",currentBalance));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return file;

    }

    public File getFile() {
        return file;
    }

    public String getCurrentDate(){

        String pattern = "MM/dd/YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    public String getCurrentTime(){
          String pattern = "hh:mm:ss a";
          SimpleDateFormat timeFormat = new SimpleDateFormat(pattern);
          Date date = new Date();
          String currentTime = timeFormat.format(date);

          return currentTime;
    }
}
