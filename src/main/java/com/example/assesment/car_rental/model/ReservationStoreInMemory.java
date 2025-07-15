package com.example.assesment.car_rental.model;

import java.util.ArrayList;
import java.util.List;

public class ReservationStoreInMemory implements ReservationStore{
    List<ReservedCar> list;
    public ReservationStoreInMemory(){
        this.list = new ArrayList<>();
    }
    public synchronized void addReservedCar(ReservedCar car){
        this.list.add(car);
    }

    public synchronized List<ReservedCar> getAll() {
        return new ArrayList<>(this.list);
    }
}
