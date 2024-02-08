package com.techelevator;

public class Cat extends Animal{
    @Override
    public String makeASound() {
        return "Meow, Meow, Meow";
    }

    @Override
    public String getSpecies() {
        return "Cat";
    }
}
