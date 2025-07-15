package com.example.assesment.car_rental.model;

import java.util.HashMap;
import java.util.Map;

public class InventoryInMemory implements Inventory{
    
    private Map<CarKey, Integer> store;

    private Map<CarKey, Integer> reserved;

    public InventoryInMemory(){
        store = new HashMap<>();
        reserved = new HashMap<>();
    }

    public void addCar(Car car, int quantity) {
        CarKey key = new CarKey(car.getModel(), car.getName(), car.getType());
        store.merge(key, quantity, Integer::sum);
    }

    public int getQuantity(Car car){
        return store.getOrDefault(new CarKey(car.getModel(), car.getName(), car.getType()), 0);
    }

    public synchronized boolean doReserved(Car car){
        CarKey key = new CarKey(car.getModel(), car.getName(), car.getType());
        int quantity = getQuantity(car);
        int reservedQuantity = reserved.getOrDefault(key, 0);
        if(quantity > reservedQuantity){
            System.out.println("Reserving car "+ reservedQuantity);
            reserved.put(key, reservedQuantity + 1);
            System.out.println(reserved);
            return true;
        }else{
            System.out.println("Reservation not possible due to lack of quantity");
            return false;
        }
        
    }
}
