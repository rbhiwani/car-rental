package com.example.assesment.car_rental.service;

import java.time.LocalDateTime;

import com.example.assesment.car_rental.model.Car;
import com.example.assesment.car_rental.model.ReservedCar;

public class ReservationService{

    private final InventoryService inventoryService;
    private final ReservationStoreService reservationStoreService;

    public ReservationService(InventoryService inventoryService, ReservationStoreService reservationStoreService){
        this.inventoryService = inventoryService;
        this.reservationStoreService = reservationStoreService;
    }

    public void reserveCar(Car car, int days, String customer){
        if(days <= 0){
            System.out.println("Car can not be reserved for days " + days);
            return;
        }
        if(customer == null || customer.isEmpty()){
            System.out.println("Car can not be reserved for customer " + customer);
            return;
        }
        double rent = car.calculateRent(days);
        boolean isReserved = inventoryService.doReserved(car);
        if(isReserved){
            ReservedCar reservedCar = new ReservedCar(car, customer, days, rent, LocalDateTime.now());
            reservationStoreService.addReservedCar(reservedCar);
        }
    }

}
