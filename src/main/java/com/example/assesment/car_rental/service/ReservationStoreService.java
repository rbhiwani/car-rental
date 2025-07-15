package com.example.assesment.car_rental.service;

import com.example.assesment.car_rental.model.ReservationStore;
import com.example.assesment.car_rental.model.ReservedCar;

public class ReservationStoreService{
    private final ReservationStore reservationStore;

    public ReservationStoreService(ReservationStore reservationStore){
        this.reservationStore = reservationStore;
    }

    public void addReservedCar(ReservedCar car){
        this.reservationStore.addReservedCar(car);
    }
}
