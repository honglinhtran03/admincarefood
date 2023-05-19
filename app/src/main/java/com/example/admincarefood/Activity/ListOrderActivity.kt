package com.example.admincarefood.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Adapter.ListOrderAdapter
import com.example.admincarefood.Database.Order
import com.example.admincarefood.MainActivity
import com.example.admincarefood.R

class ListOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_order)

        btnBackSetOnClickListener()

        recycleViewListOrder()
    }

    private fun recycleViewListOrder() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVieưListOrder)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        val getOrder = Order()
        getOrder.getListOrder { listOrder ->
            recyclerView.adapter = ListOrderAdapter(listOrder)
        }
    }

    private fun btnBackSetOnClickListener() {
        var btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this@ListOrderActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}