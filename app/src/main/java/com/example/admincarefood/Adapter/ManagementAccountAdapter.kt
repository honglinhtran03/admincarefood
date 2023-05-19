package com.example.admincarefood.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Database.Order
import com.example.admincarefood.Domain.AccountDomain
import com.example.admincarefood.R

class ManagementAccountAdapter( var listAccount: ArrayList<AccountDomain>) : RecyclerView.Adapter<ManagementAccountAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_account, parent, false)
        return ViewHolder(inflate)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var account = listAccount[position]
        holder.tvName.text = account.Name
        holder.tvEmail.text = account.Email
        val getOrder = Order()
        getOrder.getTotalOrderByIdUser(account.Id) { numberTotal ->
            holder.tvTotalOrder.text = numberTotal.toString()
        }



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
        return listAccount.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvEmail: TextView
        var tvTotalOrder: TextView


        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvTotalOrder = itemView.findViewById(R.id.tvTotalOrder)
        }
    }
}