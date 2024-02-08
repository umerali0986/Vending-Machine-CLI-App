package com.techelevator;

public class Pony extends Animal{
    @Override
    public String makeASound() {
        return "Neigh, Neigh, Yay!";
    }

    @Override
    public String getSpecies() {
        return "Pony";
    }
}
