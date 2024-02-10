package com.techelevator;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachineTest {

    @Test
    public void getItems_should_return_list_of_item_when_it_gets_called(){
        //Arrange
        VendingMachine vendingMachine = new VendingMachine();

        // Act
        List<Item> actual = vendingMachine.getItems();

        //Assert
        Assert.assertNotNull(actual);
        Assert.assertEquals(16,actual.size());

    }
}
