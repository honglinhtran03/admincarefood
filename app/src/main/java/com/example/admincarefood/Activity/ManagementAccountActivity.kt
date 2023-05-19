package com.example.admincarefood.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincarefood.Adapter.ManagementAccountAdapter
import com.example.admincarefood.Database.Account
import com.example.admincarefood.MainActivity
import com.example.admincarefood.R

class ManagementAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_account)

        btnBackSetOnClickListener()

        recycleViewManagementAccount()
    }

    private fun recycleViewManagementAccount() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAccount)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listAccount = Account()
        listAccount.getListAccount { listAccountt ->
            recyclerView.adapter = ManagementAccountAdapter(listAccountt)
        }
    }

    private fun btnBackSetOnClickListener() {
        var btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this@ManagementAccountActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}