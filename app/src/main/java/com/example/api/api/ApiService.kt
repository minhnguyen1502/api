package com.example.api.api

import com.example.api.model.DeleteResponse
import com.example.api.model.DrinkResponse
import com.example.api.model.FoodResponse
import com.example.api.model.LocationResponse
import com.example.api.model.Order
import com.example.api.model.OrderResponse
import com.example.api.model.Vehicle
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    // Create
    @POST("/v1/order")
    fun createOrder(@Body order: Order): Call<OrderResponse>

    // Read (get all items)
    @GET("/v1/food")
    fun getFood(): Call<List<FoodResponse>>

    @GET("/v1/drink")
    fun getDrink(): Call<List<DrinkResponse>>

    @GET("/v1/location/receive")
    fun getLocation(): Call<List<LocationResponse>>

    @GET("/api/v1/cars")
//    fun getCar(): Call<List<ApiResponse>>
    fun getCar(): Call<List<Vehicle>>

    @GET("/api/v1/cars/{id}")
    fun getDetailCar(@Path("id") id: Int): Call<Vehicle>

    // Delete
    @DELETE("/v1/cancel/{id}")
    fun cancelOrder(@Path("id") id: Int): Call<DeleteResponse>
}
