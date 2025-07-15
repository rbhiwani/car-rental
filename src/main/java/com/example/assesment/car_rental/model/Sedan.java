package com.example.assesment.car_rental.model;

public class Sedan extends Car{

    public Sedan(String model, String name, double rate){
        super(model, name, rate);
    }

    public double calculateRent(int days){
        return this.rate * days;
    }

    public String getType(){
        return "Sedan";
    }
}
