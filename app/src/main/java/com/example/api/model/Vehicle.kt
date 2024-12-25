package com.example.api.model

// Model class for the vehicle
data class Vehicle(
    val id: Int,
    val make: String,
    val model: String,
    val year: Int,
    val color: String,
    val mileage: Int,
    val price: Int,
    val fuelType: String,
    val transmission: String,
    val engine: String,
    val horsepower: Int,
    val features: List<String>,
    val owners: Int,
    val image: String
)

// API Response Wrapper (if needed)
data class ApiResponse(
    val vehicles: List<Vehicle>
)
