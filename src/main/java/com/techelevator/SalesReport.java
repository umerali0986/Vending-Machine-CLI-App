package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class SalesReport {

    public void addSale(Item item, File file) {
        try(Scanner scanner = new Scanner(file) ; PrintWriter printWriter = new PrintWriter(file)) {

        }
        catch(FileNotFoundException e) {

        }
    }
}
