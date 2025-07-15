package com.example.assesment.car_rental.service;

import com.example.assesment.car_rental.model.Car;
import com.example.assesment.car_rental.model.Inventory;

public class InventoryService {

    private final Inventory inventory;

    public InventoryService(Inventory inventory){
        this.inventory = inventory;
    }

    public boolean doReserved(Car car){
        return inventory.doReserved(car);
    }

    public void addCar(Car car, int quantity){
        inventory.addCar(car, quantity);
    }

}
