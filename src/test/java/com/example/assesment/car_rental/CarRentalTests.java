package com.example.assesment.car_rental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.assesment.car_rental.model.Car;
import com.example.assesment.car_rental.model.Inventory;
import com.example.assesment.car_rental.model.InventoryInMemory;
import com.example.assesment.car_rental.model.ReservationStore;
import com.example.assesment.car_rental.model.ReservationStoreInMemory;
import com.example.assesment.car_rental.model.ReservedCar;
import com.example.assesment.car_rental.model.Sedan;
import com.example.assesment.car_rental.service.InventoryService;
import com.example.assesment.car_rental.service.ReservationService;
import com.example.assesment.car_rental.service.ReservationStoreService;

public class CarRentalTests {
    private Inventory inventory;
    private InventoryService inventoryService;
    private ReservationStore reservationStore;
    private ReservationStoreService reservationStoreService;
    private ReservationService reservationService;
    private Car sedan;

    @BeforeEach
    void setup() {
        inventory = new InventoryInMemory();
        inventoryService = new InventoryService(inventory);
        reservationStore = new ReservationStoreInMemory();
        reservationStoreService = new ReservationStoreService(reservationStore);
        reservationService = new ReservationService(inventoryService, reservationStoreService);

        sedan = new Sedan("Toyota", "Camry", 50.0);
        inventory.addCar(sedan, 5); // Add 5 sedans
    }

    @Test
    void testSuccessfulReservation() {
        reservationService.reserveCar(sedan, 3, "Alice");

        assertEquals(1, reservationStore.getAll().size());
        ReservedCar reserved = reservationStore.getAll().get(0);
        assertEquals("Alice", reserved.customer());
        assertEquals(3, reserved.days());
        assertEquals(150.0, reserved.totalPrice());
    }

    @Test
    void testReservationBeyondAvailableQuantity() {
        for (int i = 0; i < 5; i++) {
            reservationService.reserveCar(sedan, 1, "User" + i);
        }

        // 6th attempt should fail
        reservationService.reserveCar(sedan, 1, "User6");

        assertEquals(5, reservationStore.getAll().size());
    }

    @Test
    void testThreadSafetyWithConcurrentReservations() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        AtomicInteger successCount = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            int user = i;
            executor.submit(() -> {
                reservationService.reserveCar(sedan, 1, "User" + user);
                if (inventory.getQuantity(sedan) >= successCount.incrementAndGet()) {
                    
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // Should not exceed the inventory count
        int reservedCount = reservationStore.getAll().size();
        assertTrue(reservedCount <= 5, "Reserved count should not exceed available cars");
    }

    @Test
    public void testAddCar(){
        Car someCar = new Sedan("X", "Y", 10);
        inventory.addCar(someCar, 10);

        assertEquals(10, inventory.getQuantity(someCar));
    }

}
