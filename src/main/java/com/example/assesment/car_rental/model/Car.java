package com.example.assesment.car_rental.model;

public abstract class Car{
    private String model;
    private String name;
    protected double rate;

    Car(String model, String name, double rate){
        this.model = model;
        this.name = name;
        this.rate = rate;
    }
    public abstract double calculateRent(int days);

    public abstract String getType();

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
