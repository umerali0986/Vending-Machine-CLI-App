package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SalesReportTest {

    @Test
    public void addSales_should_return_updated_itemQuantity_when_item_already_exist_in_map(){

        // Arrange
        SalesReport salesReport = new SalesReport();
        Map<String,Integer> map = new HashMap<>();
        map.put("lollipop",0);
        Item item = new Item();
        item.setName("lollipop");

        // Act
        Map<String, Integer> actual = salesReport.addSale(item,map);
        map.put("lollipop",1);

        //Assert
        Assert.assertEquals(map,actual);
    }


    @Test
    public void addSales_should_return_theSame_map_when_item_doesNot_exist_in_map(){

        // Arrange
        SalesReport salesReport = new SalesReport();
        Map<String,Integer> map = new HashMap<>();
        map.put("lollipop",0);
        Item item = new Item();
        item.setName("candy");

        // Act
        Map<String, Integer> actual = salesReport.addSale(item,map);

        //Assert
        Assert.assertEquals(map,actual);
    }

}