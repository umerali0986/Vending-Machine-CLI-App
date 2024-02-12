package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private File file;

    public String addTransaction(File file, String transactionName, double deposit, double currentBalance){
        //adding every transactions to Vending.log file
        String returnValue = "";

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(file,true))){
                returnValue = getCurrentDate() + " " + getCurrentTime() + " " + transactionName +
                        ((transactionName.equals("FEED MONEY") || transactionName.equals("GIVE CHANGE")) ?  ": " : " ")
                        + "$" + String.format("%.2f",deposit) + " $" + String.format("%.2f",currentBalance);
            writer.println(returnValue);

        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return returnValue;

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
