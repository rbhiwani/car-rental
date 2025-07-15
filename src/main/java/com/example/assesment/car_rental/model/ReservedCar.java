package com.example.assesment.car_rental.model;

import java.time.LocalDateTime;

public record ReservedCar(Car car,
String customer,
int days,
double totalPrice,
LocalDateTime time) {
}
