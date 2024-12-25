package com.example.api.home

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.adapter.VehicleAdapter
import com.example.api.databinding.ActivityMainBinding
import com.example.foodorder.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val mainViewModel: MainViewModel by viewModels()
    val TAG = "minh"

    override fun initView() {
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        mainViewModel.foodList.observe(this, Observer { food ->
            food?.forEach { Log.e(TAG, "$it") }
        })

        mainViewModel.drinkList.observe(this, Observer { drink ->
            drink?.forEach { Log.e(TAG, "$it") }
        })

        mainViewModel.locationList.observe(this, Observer{location ->
            location?.forEach { Log.e(TAG, "$it", ) }
        })

        mainViewModel.carList.observe(this, Observer{car ->
            binding.recycleview.adapter = VehicleAdapter(car)
            car?.forEach { Log.e(TAG, "$it", ) }
        })

        mainViewModel.error.observe(this, Observer { errorMessage ->
            Log.e(TAG, "Error: $errorMessage")
        })

        // Gọi dữ liệu
        mainViewModel.getFood()
        mainViewModel.getDrink()
        mainViewModel.getLocation()
        mainViewModel.getCar()
    }

    override fun bindView() {

    }
}
