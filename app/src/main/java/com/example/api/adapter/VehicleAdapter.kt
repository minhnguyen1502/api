package com.example.api.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.R
import com.example.api.detail.DetailActivity
import com.example.api.model.Vehicle

class VehicleAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMakeModel: TextView = itemView.findViewById(R.id.tvMakeModel)
        val img: ImageView = itemView.findViewById(R.id.ivImage)
        val tvYear: TextView = itemView.findViewById(R.id.tvYear)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("vehicle_id", vehicle.id)
            context.startActivity(intent)
        }
        Glide.with(holder.itemView.context)
            .load(vehicle.image) // URL của hình ảnh
            .placeholder(R.drawable.loading) // Ảnh mặc định khi đang tải
            .error(R.drawable.error) // Ảnh hiển thị khi có lỗi
            .into(holder.img)
        holder.tvMakeModel.text = "${vehicle.make} ${vehicle.model}"
        holder.tvYear.text = "Year: ${vehicle.year}"
        holder.tvPrice.text = "Price: $${vehicle.price}"
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

}
