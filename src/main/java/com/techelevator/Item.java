package com.techelevator;

import java.sql.SQLOutput;

public class Item {
    private String name;
    private String slot;
    private double price;
    private int quantity = 5;
    private Animal animal;




    @Override
    public String toString(){

        return "Slot = " + getSlot() + ", Name = " + getName() + ", Price = " + String.format("%.2f", getPrice()) + ", Type = " +  getAnimal().getSpecies()
                + (isSoldOut() ? ", Sold out " : ", Quantity = " + getQuantity());
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Animal getAnimal() {
        return animal;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public void setAnimal(String animal) {
        if(animal.equalsIgnoreCase("cat")){
            this.animal = new Cat();
        }
        else if(animal.equalsIgnoreCase("Penguin")){
            this.animal = new Penguin();
        }
        else if(animal.equalsIgnoreCase("Duck")){
            this.animal = new Duck();
        }
        else if(animal.equalsIgnoreCase("Pony")){
            this.animal = new Pony();
        }
    }
    public boolean isSoldOut(){
        return quantity < 1 ? true: false;
    }

}
