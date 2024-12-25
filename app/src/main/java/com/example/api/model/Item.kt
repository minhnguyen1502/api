package com.example.api.model

data class FoodResponse(
    val code: Int,
    val msg: String,
    val data: FoodData
)

data class FoodData(
    val title: String,
    val list_food: List<FoodItem>
)

data class FoodItem(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int
)

data class DrinkResponse(
    val code: Int,
    val msg: String,
    val data: DrinkData
)

data class DrinkData(
    val title: String,
    val list_drink: List<DrinkItem>
)

data class DrinkItem(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val discount: Int,
    val percent_discount: Int
)

data class LocationResponse(
    val code: Int,
    val msg: String,
    val data: LocationData
)

data class LocationData(
    val title: String,
    val list_location: List<LocationItem>
)

data class LocationItem(
    val id: Int,
    val name: String,
    val distance: String
)
data class Order(
    val id: Int,
    val name: String,
    val price: Int
)

data class OrderResponse(
    val code: Int,
    val msg: String,
    val data: String
)
data class DeleteResponse(
    val code: Int,
    val msg: String,
    val data: String
)

