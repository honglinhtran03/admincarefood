package com.example.admincarefood.Database

import android.annotation.SuppressLint
import com.example.admincarefood.Domain.OrderDetailDomain
import com.example.admincarefood.Domain.OrderDomain
import com.google.firebase.database.*

class Order {

    private lateinit var dbReference: DatabaseReference

    fun getOrderByIdUser(idUser:String, callback: (ArrayList<OrderDomain>) -> Unit) {
        val listOrder = ArrayList<OrderDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Order")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOrder.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDomain::class.java)
                        if (order != null) {
                            if(order.IdUser==idUser) {
                                listOrder.add(order)
                            }
                        }

                    }
                }
                callback(listOrder)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getListOrder(callback: (ArrayList<OrderDomain>) -> Unit) {
        val listOrder = ArrayList<OrderDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Order")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOrder.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDomain::class.java)
                        if (order != null) {
                            listOrder.add(order)
                        }

                    }
                }
                callback(listOrder)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getOrder(idOrder:String, callback: (OrderDomain) -> Unit) {
        dbReference = FirebaseDatabase.getInstance().getReference("Order")

        dbReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDomain::class.java)
                        if (order != null) {
                            if (order.Id==idOrder)
                            callback(order)
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getTotalOrderByIdUser(idUser:String, callback: (Int) -> Unit) {
        val listOrder = ArrayList<OrderDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Order")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOrder.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDomain::class.java)
                        if (order != null) {
                            if(order.IdUser==idUser) {
                                listOrder.add(order)
                            }
                        }

                    }
                }
                callback(listOrder.size)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getOrderDetail(idOrder:String, callback: (ArrayList<OrderDetailDomain>) -> Unit) {
        val listCart = ArrayList<OrderDetailDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Order Detail")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listCart.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDetailDomain::class.java)
                        if (order != null) {
                            if(order.IdOrder==idOrder) {
                                listCart.add(order)
                            }
                        }

                    }
                }
                callback(listCart)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun updateStatusOrder(idOrder:String, status:Int) {
        dbReference = FirebaseDatabase.getInstance().getReference("Order")

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val order = value.getValue(OrderDomain::class.java)
                        if (order != null) {
                            if(order.Id==idOrder) {
                                val newOrder = OrderDomain(order.Id, order.IdUser, status, order.Address, order.NumberPhone, order.DateTime )
                                dbReference.child(order.Id).setValue(newOrder)
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