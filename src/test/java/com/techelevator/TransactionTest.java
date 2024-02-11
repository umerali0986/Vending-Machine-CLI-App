package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

//    @Test
//    //TODO- create a test for file
//    public void addTransaction_should_return_valid_transaction(){
//        //Arrange
//        Transaction transaction = new Transaction();
//
//        // Act
//        String actual = transaction.addTransaction("FEED MONEY",20.00,30.00);
//        String expect= "";
//        File file = new File("Vending.log");
//        try(Scanner scanner = new Scanner(file)){
//            while (scanner.hasNextLine()){
//                String stringLine = scanner.nextLine();
//                if((stringLine.contains("FEED MONEY")) && (stringLine.contains("20.00")) && (stringLine.contains("30.00"))){
//                    expect = stringLine;
//                    break;
//                }
//
//            }
//        }catch (FileNotFoundException e){
//
//        }
//
//        //Assert
//        Assert.assertEquals(expect,actual);
//
//    }
}