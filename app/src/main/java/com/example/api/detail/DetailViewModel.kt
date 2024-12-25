package com.example.api.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.api.ApiService
import com.example.api.api.RetrofitClient
import com.example.api.model.Order
import com.example.api.model.OrderResponse
import com.example.api.model.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {

    val api = RetrofitClient.instance.create(ApiService::class.java)
    var count = 0
    val totalPrice = MutableLiveData<String>()

    private val _car = MutableLiveData<Vehicle>()
    val car: LiveData<Vehicle> = _car

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _orderStatus = MutableLiveData<String>()
    val orderStatus: LiveData<String> = _orderStatus


    fun getCar(id: Int) {
        api.getDetailCar(id).enqueue(object : Callback<Vehicle> {
            override fun onResponse(call: Call<Vehicle>, response: Response<Vehicle>) {
                if (response.isSuccessful) {
                    _car.value = response.body()
                }
            }

            override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                _error.value = t.message
            }
        })
    }

    fun updateQuantity(isIncrease: Boolean) {
        if (isIncrease) {
            count++
        } else if (count > 0) {
            count--
        }
//        calculateTotalPrice()
    }

//    private fun calculateTotalPrice() {
//        val price = _car.value?.price ?: 0
//        val total = price * count
//        totalPrice.postValue("Total Price: $${"%.2f".format(total)}")
//    }

    fun addToCart() {
        val car = _car.value
        if (car != null) {
            val order = Order(
                id = car.id,
                name = car.model,
                price = car.price
            )

            // Call the createOrder API
            api.createOrder(order).enqueue(object : Callback<OrderResponse> {
                override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.isSuccessful) {
                        val orderResponse = response.body()
                        if (orderResponse != null && orderResponse.code == 200) {
                            _orderStatus.value = "Order successfully created: ${orderResponse.data}"
                        } else {
                            _orderStatus.value = "Failed to create order: ${orderResponse?.msg}"
                        }
                    } else {
                        _orderStatus.value = "Failed to create order: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    _orderStatus.value = "Error creating order: ${t.message}"
                }
            })
        } else {
            _orderStatus.value = "Car details are missing."
        }
    }

}