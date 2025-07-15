package com.example.assesment.car_rental;

import java.util.concurrent.CompletableFuture;

import com.example.assesment.car_rental.model.Inventory;
import com.example.assesment.car_rental.model.InventoryInMemory;
import com.example.assesment.car_rental.model.ReservationStore;
import com.example.assesment.car_rental.model.ReservationStoreInMemory;
import com.example.assesment.car_rental.model.Sedan;
import com.example.assesment.car_rental.service.InventoryService;
import com.example.assesment.car_rental.service.ReservationService;
import com.example.assesment.car_rental.service.ReservationStoreService;



public class CarRental {

    // Car
     // type
     // model
     // name
     // id
     
     // Inventory
      // car id
      // quantity

    // Reservation
        // car id
        // user id
        // quantity


        


        public static void main(String[] args) {
            Inventory inventory = new InventoryInMemory();
            InventoryService inventoryService = new InventoryService(inventory);
            Sedan  sedan = new Sedan("Desire", "Swift", 10);
            inventoryService.addCar(sedan, 2);
            ReservationStore reservationStore = new ReservationStoreInMemory();
            ReservationStoreService reservationStoreService = new ReservationStoreService(reservationStore);
            ReservationService reservationService = new ReservationService(inventoryService, reservationStoreService);

            //reservationService.reserveCar(sedan, 2, "raj");

            CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> reservationService.reserveCar(sedan, 2, "a")),
                CompletableFuture.runAsync(() -> reservationService.reserveCar(sedan, 2, "b")),
                CompletableFuture.runAsync(() -> reservationService.reserveCar(sedan, 2, "c"))).join();
        }

}
