package com.example.admincarefood.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.admincarefood.Domain.DishDomain
import com.example.admincarefood.R
import com.google.firebase.database.FirebaseDatabase

class AddDishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dish)

        val btnAddDish = findViewById<Button>(R.id.button)
        btnAddDish.setOnClickListener {
            addDish()
        }

    }

    private fun addDish() {
        var databaseReference = FirebaseDatabase.getInstance().getReference("Dish")
        var id = findViewById<TextView>(R.id.edtId).text.toString().toInt()
        var name = findViewById<TextView>(R.id.edtName).text.toString()
        var idCategory = findViewById<TextView>(R.id.edtIdCategory).text.toString().toInt()
        var price = findViewById<TextView>(R.id.edtPrice).text.toString().toDouble()
        var image = findViewById<TextView>(R.id.edtImage).text.toString()

        var dish = DishDomain(id,name, price, image, 20, 4.5, 350, idCategory, 0, "abc" )
        databaseReference.child(id.toString()).setValue(dish)
    }
}