package com.techelevator;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseTest {

//    @Test
//    public void feedMoney_should_return_10_when_10_passed() {
//        //Arrange
//        Purchase purchase = new Purchase();
//        String deposited = "10";
//
//        //Act
//        double actual = purchase.feedMoney(deposited);
//        //Assert
//        Assert.assertEquals(10.0, actual, 0.01);
//
//        //Act
//         actual = purchase.feedMoney(deposited);
//        //Assert
//        Assert.assertEquals(20.0, actual, 0.01);
//    }

    @Test
    public void selectProduct_should_return_isSoldOut_when_item_quantity_is_less_than_1() {
        //Arrange
        Purchase purchase = new Purchase();
        Item item1 = new Item("lollipop", "A1", 0.50, 0, new Cat());
        List<Item> items = new ArrayList<>();
        items.add(item1);
        String slotNumber = "A1";
        //Act
        String actual = purchase.selectProduct(items, slotNumber);
        //Assert
        Assert.assertEquals(item1.getName() + " is sold out.", actual);

    }
    @Test
    public void selectProduct_should_return_invalidSlotCode_when_invalid_slot_is_passed() {
        //Arrange
        Purchase purchase = new Purchase();
        Item item1 = new Item("lollipop", "B1", 0.50, 0, new Cat());
        List<Item> items = new ArrayList<>();
        items.add(item1);
        String slotNumber = "A1";
        //Act
        String actual = purchase.selectProduct(items, slotNumber);
        //Assert
        Assert.assertEquals("Invalid slot code.", actual);
    }

//    @Test
//    public void selectProduct_should_return_correct_message_when_valid_slot_passed() {
//            //Arrange
//            Purchase purchase = new Purchase();
//            purchase.setCurrentBalance(5.00);
//            Item item1 = new Item("lollipop", "A1", 0.50, 4, new Cat());
//            List<Item> items = new ArrayList<>();
//            items.add(item1);
//            String slotNumber = "A1";
//            //Act
//            String actual = purchase.selectProduct(items, slotNumber);
//            String expected = item1.getName() + " | $" + String.format("%.2f", item1.getPrice()) + " | $"
//                    + String.format("%.2f", purchase.getCurrentBalance()) + " | Meow, Meow, Meow";
//            //Assert
//            Assert.assertEquals(expected, actual);
//    }

    @Test
    public void dispenseItem_should_return_insufficientFunds_when_current_balance_is_less_than_item_price() {
        //Arrange
        Purchase purchase = new Purchase();
        Item item1 = new Item("lollipop", "A1", 0.50, 4, new Cat());
        List<Item> items = new ArrayList<>();
        items.add(item1);
        //Act
        String actual = purchase.dispenseItem(item1);
        String expected = "Insufficient funds.";
        //Assert
        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void dispenseItem_should_return_correct_message_when_current_balance_greater_or_equal_to_item_price() {
//        //Arrange
//        Purchase purchase = new Purchase();
//        purchase.setCurrentBalance(5.00);
//        Item item1 = new Item("lollipop", "A1", 0.50, 4, new Cat());
//        List<Item> items = new ArrayList<>();
//        items.add(item1);
//        String slotNumber = "A1";
//        //Act
//        String actual = purchase.selectProduct(items, slotNumber);
//        String expected = item1.getName() + " | $" + String.format("%.2f", item1.getPrice()) + " | $"
//                + String.format("%.2f", purchase.getCurrentBalance()) + " | Meow, Meow, Meow";
//        //Assert
//        Assert.assertEquals(expected, actual);
//    }

//    @Test
//    public void finishTransaction_should_return_correct_message_when_change_amount_required_passed() {
//        //Arrange
//        Purchase purchase = new Purchase();
//
//        //Act
//        String actual = purchase.finishTransaction(9.00);
//        String expected = "36 $0.25";
//
//        //Assert
//        Assert.assertEquals(expected, actual);
//    }
}
