package com.example.admincarefood.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Adapter.ManagementAccountAdapter
import com.example.admincarefood.Adapter.RevenueAdapter
import com.example.admincarefood.Database.Account
import com.example.admincarefood.Database.Dish
import com.example.admincarefood.MainActivity
import com.example.admincarefood.R

class RevenueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)

        btnBackSetOnClickListener()

        recycleViewRevenue()
    }

    private fun recycleViewRevenue() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRevenue)
        var tvTotalRevenue = findViewById<TextView>(R.id.tvTotalRevenue)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val getDish = Dish()
        getDish.getListDish { listDish ->
            recyclerView.adapter = RevenueAdapter(listDish)
            var total = 0.0
            for (dish in listDish) {
                total += dish.Price*dish.Sold
                total = Math.round(total*100.0)/100.0
            }
            tvTotalRevenue.text = total.toString()
        }
    }

    private fun btnBackSetOnClickListener() {
        var btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this@RevenueActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}