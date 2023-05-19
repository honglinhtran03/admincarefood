package com.example.admincarefood.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admincarefood.Domain.DishDomain
import com.example.admincarefood.R

class RevenueAdapter( var listSold: ArrayList<DishDomain>) : RecyclerView.Adapter<RevenueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_item_sold, parent, false)
        return ViewHolder(inflate)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var dish = listSold[position]
        holder.tvName.text = dish.Name
        holder.tvPrice.text = dish.Price.toString()
        holder.tvSold.text = dish.Sold.toString()

        val drawableRecouseId = holder.itemView.context.resources.getIdentifier(
            dish.Image, "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context).load(drawableRecouseId).into(holder.img)


//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context, StatusOrderActivity::class.java)
//            intent.putExtra("idOrder", order.Id)
//            intent.putExtra("idUser", idUser)
//
//            holder.itemView.context.startActivity(intent)
//
//        }

    }


    override fun getItemCount(): Int {
        return listSold.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var tvName: TextView
        var tvPrice: TextView
        var tvSold: TextView


        init {
            img = itemView.findViewById(R.id.img)
            tvName = itemView.findViewById(R.id.tvName)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvSold = itemView.findViewById(R.id.tvSold)
        }
    }
}