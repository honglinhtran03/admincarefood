package com.example.admincarefood.Database

import com.example.admincarefood.Domain.DishDomain
import com.google.firebase.database.*

class Dish {
    private lateinit var dbReference: DatabaseReference

    fun getListDish(callback: (ArrayList<DishDomain>) -> Unit) {
        val lishDish = ArrayList<DishDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Dish")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lishDish.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val dish = value.getValue(DishDomain::class.java)
                        if (dish != null) {
                            lishDish.add(dish)
                        }
                    }
                }
                callback(lishDish)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getDish(idDish:Int, callback: (DishDomain) -> Unit) {
        dbReference = FirebaseDatabase.getInstance().getReference("Dish")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val dish = value.getValue(DishDomain::class.java)
                        if (dish != null) {
                            if(dish.Id==idDish) {
                                callback(dish)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }
}