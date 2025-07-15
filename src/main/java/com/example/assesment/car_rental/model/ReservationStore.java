package com.example.assesment.car_rental.model;


import java.util.List;

public interface ReservationStore{
    
    public void addReservedCar(ReservedCar car);

    public List<ReservedCar> getAll();
}
