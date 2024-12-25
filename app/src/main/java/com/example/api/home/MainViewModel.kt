package com.example.api.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.api.ApiService
import com.example.api.model.DrinkResponse
import com.example.api.model.FoodResponse
import com.example.api.model.LocationResponse
import com.example.api.api.RetrofitClient
import com.example.api.model.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val api = RetrofitClient.instance.create(ApiService::class.java)
    private val _foodList = MutableLiveData<List<FoodResponse>>()
    val foodList: LiveData<List<FoodResponse>> = _foodList

    private val _drinkList = MutableLiveData<List<DrinkResponse>>()
    val drinkList: LiveData<List<DrinkResponse>> = _drinkList

    private val _locationList = MutableLiveData<List<LocationResponse>>()
    val locationList: LiveData<List<LocationResponse>> = _locationList

    private val _carList = MutableLiveData<List<Vehicle>>()
    val carList: LiveData<List<Vehicle>> = _carList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getFood(){
        api.getFood().enqueue(object : Callback<List<FoodResponse>> {
            override fun onResponse(
                call: Call<List<FoodResponse>>,
                response: Response<List<FoodResponse>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        _foodList.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<FoodResponse>>, t: Throwable) {
                _error.value = t.message
            }
        })
    }

    fun getDrink(){
        api.getDrink().enqueue(object : Callback<List<DrinkResponse>> {
            override fun onResponse(
                call: Call<List<DrinkResponse>>,
                response: Response<List<DrinkResponse>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        _drinkList.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<DrinkResponse>>, t: Throwable) {
                _error.value = t.message
            }
        })
    }

    fun getLocation(){
        api.getLocation().enqueue(object : Callback<List<LocationResponse>> {
            override fun onResponse(
                call: Call<List<LocationResponse>>,
                response: Response<List<LocationResponse>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        _locationList.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
                _error.value = t.message
            }
        })
    }

    fun getCar(){
        api.getCar().enqueue(object : Callback<List<Vehicle>> {
            override fun onResponse(
                call: Call<List<Vehicle>>,
                response: Response<List<Vehicle>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        _carList.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}