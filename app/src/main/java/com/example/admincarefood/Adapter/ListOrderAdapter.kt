package com.example.admincarefood.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Activity.DetailOrderActivity
import com.example.admincarefood.Database.Account
import com.example.admincarefood.Domain.OrderDomain
import com.example.admincarefood.R


class ListOrderAdapter(var listOrder: ArrayList<OrderDomain>) : RecyclerView.Adapter<ListOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_order, parent, false)
        return ViewHolder(inflate)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var order = listOrder[position]
        holder.tvIdOrder.text = order.Id
        holder.tvAddress.text = order.Address
        holder.tvNumberPhone.text = order.NumberPhone

        val getAccount = Account()

        getAccount.getAccount(order.IdUser) { account->
            holder.tvName.text = account.Name
        }

        when(order.Status) {
            1 -> holder.tvStatus.text = "Preparing"
            2 -> holder.tvStatus.text = "Transported"
            3 -> holder.tvStatus.text = "Arrived"
            else -> holder.tvStatus.text = "Preparing"
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailOrderActivity::class.java)
            intent.putExtra("idOrder", order.Id)
            holder.itemView.context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return listOrder.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvIdOrder: TextView
        var tvName: TextView
        var tvStatus: TextView
        var tvTotalPrice: TextView
        var tvAddress: TextView
        var tvNumberPhone: TextView


        init {
            tvIdOrder = itemView.findViewById(R.id.idOrder)
            tvName = itemView.findViewById(R.id.tvName)
            tvStatus = itemView.findViewById(R.id.tvStatus)
            tvTotalPrice = itemView.findViewById(R.id.tvPrice)
            tvAddress = itemView.findViewById(R.id.tvAddress)
            tvNumberPhone = itemView.findViewById(R.id.tvNumberPhone)
        }
    }
}