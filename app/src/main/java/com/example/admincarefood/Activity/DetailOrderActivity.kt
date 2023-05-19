package com.example.admincarefood.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Adapter.OrderDetailAdapter
import com.example.admincarefood.Database.Order
import com.example.admincarefood.MainActivity
import com.example.admincarefood.R

class DetailOrderActivity : AppCompatActivity() {
    private lateinit  var idOrder:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)

        var i = intent
        if(i.getStringExtra("idOrder")!=null) {
            idOrder = i.getStringExtra("idOrder").toString()
        } else
            idOrder = "1"

        btnBackSetOnClickListener()


        setData()
    }

    private fun setData() {

        val getOrder = Order()
        getOrder.getOrder(idOrder) { order ->

//            val tvStatus = findViewById<TextView>(R.id.tvStatus)
            val tvID = findViewById<TextView>(R.id.tvId)
            val tvDateTime = findViewById<TextView>(R.id.tvDateTime)
            val tvAddress = findViewById<TextView>(R.id.tvAddress)
            val tvPhone = findViewById<TextView>(R.id.tvNumberPhone)

            tvID.text = order.Id
            tvDateTime.text = order.DateTime
            tvAddress.text = order.Address
            tvPhone.text = order.NumberPhone
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDishOrder)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getOrder.getOrderDetail(idOrder) { listOrderDetail->
            recyclerView.adapter = OrderDetailAdapter(listOrderDetail)
        }

        val spiner = findViewById<Spinner>(R.id.spinner)
        var list = listOf("Preparing", "Transported", "Arrived")
        spiner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list )

        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val getOrder = Order()
                if (selectedItem == "Preparing") {
                    getOrder.updateStatusOrder(idOrder, 1)
                }

                if (selectedItem == "Transported") {
                    getOrder.updateStatusOrder(idOrder, 2)
                }
                if (selectedItem== "Arrived") {
                    getOrder.updateStatusOrder(idOrder, 3)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Xử lý sự kiện khi không có mục nào được chọn
            }
        }
    }

    private fun btnBackSetOnClickListener() {
        var btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this@DetailOrderActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}