package com.techelevator;

public class Penguin extends Animal{
    @Override
    public String makeASound() {
        return "Squawk, Squawk, Whee!";
    }

    @Override
    public String getSpecies() {
        return "Penguin";
    }
}
