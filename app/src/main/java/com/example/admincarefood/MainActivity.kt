package com.example.admincarefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Activity.AddDishActivity
import com.example.admincarefood.Activity.ListOrderActivity
import com.example.admincarefood.Activity.ManagementAccountActivity
import com.example.admincarefood.Activity.RevenueActivity
import com.example.admincarefood.Adapter.CategoryAdapter
import com.example.admincarefood.Database.GetDataMain

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCategory()

        managementAccount()
        revuenue()
        listOrder()
        add()
    }

    private fun listOrder() {
        val btnListOrder = findViewById<LinearLayout>(R.id.order)
        btnListOrder.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListOrderActivity::class.java))
        }
    }

    private fun revuenue() {
        val btnRevenue = findViewById<LinearLayout>(R.id.revenue)
        btnRevenue.setOnClickListener {
            startActivity(Intent(this@MainActivity, RevenueActivity::class.java))
        }
    }

    private fun add() {
        val btnRevenue = findViewById<LinearLayout>(R.id.addDish)
        btnRevenue.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddDishActivity::class.java))
        }
    }

    private fun managementAccount() {
        val btnManagementAccount = findViewById<LinearLayout>(R.id.account)
        btnManagementAccount.setOnClickListener {
            startActivity(Intent(this@MainActivity, ManagementAccountActivity::class.java))
        }
    }

    private fun recyclerViewCategory() {
        var recyclerViewCategoryList = findViewById<RecyclerView>(R.id.recycleViewCategory)
        recyclerViewCategoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var data = GetDataMain()
        data.getListCategory { list->
            var adt = CategoryAdapter(list)
            recyclerViewCategoryList.adapter = adt
        }
    }
}