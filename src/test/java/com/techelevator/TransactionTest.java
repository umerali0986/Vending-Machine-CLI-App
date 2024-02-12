package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.awt.print.Printable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void addTransaction_should_return_valid_transaction(){
        //Arrange
        File file = new File("VENDING_TEST.log");
        Transaction transaction = new Transaction();
        // Act
        String actual = transaction.addTransaction(file, "TEST_SAMPLE",20.00,30.00);
        String expect= "";
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String stringLine = scanner.nextLine();
                if(stringLine.contains("TEST_SAMPLE")){
                    expect = stringLine;
                    PrintWriter printWriter = new PrintWriter(file);
                    break;
                }

            }
        }catch (FileNotFoundException e){

        }
        //Assert
        Assert.assertEquals(expect,actual);

    }

}