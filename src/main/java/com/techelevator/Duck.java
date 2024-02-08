package com.techelevator;

public class Duck extends Animal{


    @Override
    public String makeASound() {
        return "Quack, Quack, Splash!";
    }

    @Override
    public String getSpecies() {
        return "Duck";
    }
}
