package com.example.admincarefood.Database

import com.example.admincarefood.Domain.AccountDomain
import com.example.admincarefood.Domain.OrderDomain
import com.google.firebase.database.*

class Account {
    private lateinit var dbReference: DatabaseReference

    fun getListAccount( callback: (ArrayList<AccountDomain>) -> Unit) {
        val listAccount = ArrayList<AccountDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Account")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listAccount.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val account = value.getValue(AccountDomain::class.java)
                        if (account != null) {
                            listAccount.add(account)
                        }

                    }
                }
                callback(listAccount)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.toException()}")
            }
        })
    }

    fun getAccount( idUser:String,  callback: (AccountDomain) -> Unit) {
        val listAccount = ArrayList<AccountDomain>()
        dbReference = FirebaseDatabase.getInstance().getReference("Account")

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listAccount.clear()
                if (snapshot.exists()) {
                    for (value in snapshot.children) {
                        val account = value.getValue(AccountDomain::class.java)
                        if (account != null) {
                            if (account.Id == idUser) {
                                callback(account)
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