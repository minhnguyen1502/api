package com.example.api.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.api.R
import com.example.api.databinding.ActivityDetailBinding
import com.example.foodorder.base.BaseActivity

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val detailViewModel: DetailViewModel by viewModels()
    private val TAG = "minh"
    private var count = 0

    override fun initView() {
        val vehicleId = intent.getIntExtra("vehicle_id", -1)
        if (vehicleId != -1) {
            Toast.makeText(this, "Vehicle ID: $vehicleId", Toast.LENGTH_SHORT).show()
            detailViewModel.getCar(vehicleId)
        }

        // Observe error messages
        detailViewModel.error.observe(this, Observer { errorMessage ->
            Log.e(TAG, "Error: $errorMessage")
        })
    }

    override fun bindView() {
        // Observe car details and update UI
        detailViewModel.car.observe(this, Observer { car ->
            car?.let {
                Glide.with(this)
                    .load(it.image) // Load car image
                    .placeholder(R.drawable.loading) // Placeholder image while loading
                    .error(R.drawable.error) // Error image
                    .into(binding.ivImage)

                // Set car details in the UI
                binding.tvMakeModel.text = "${it.make} ${it.model}"
                binding.tvYear.text = "Year: ${it.year}"
                binding.tvPrice.text = "Price: ${it.price}"
                binding.tvcolor.text = "Color: ${it.color}"
                binding.tvengine.text = "Engine: ${it.engine}"
                binding.tvfeatures.text = "Features: ${it.features}"
                binding.tvfuelType.text = "Fuel Type: ${it.fuelType}"
                binding.tvmileage.text = "Mileage: ${it.mileage}"
            }
        })

        // Handle "Increase" button click
        binding.up.setOnClickListener {
            count++
            binding.tvNumber.text = count.toString()
            detailViewModel.updateQuantity(true)  // Update quantity in ViewModel
        }

        // Handle "Decrease" button click
        binding.down.setOnClickListener {
            if (count > 0) { // Prevent negative quantity
                count--
                binding.tvNumber.text = count.toString()
                detailViewModel.updateQuantity(false)  // Update quantity in ViewModel
            }
        }

        // Handle "Add to Cart" button click
        binding.btnAdd.setOnClickListener {
            detailViewModel.addToCart()  // Add car to cart using ViewModel
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }

        // Observe total price and update the UI
        detailViewModel.totalPrice.observe(this, Observer { total ->
        })
    }
}
