package com.example.assesment.car_rental.model;

record CarKey(String model, String name, String type){}

public interface Inventory{
    

    public void addCar(Car car, int quantity);

    public int getQuantity(Car car);

    public boolean doReserved(Car car);
}
