package com.example.admincarefood.Database


import com.example.admincarefood.Domain.CategoryDomain
import com.google.firebase.database.*

class GetDataMain {

//    Hàm get category
fun getListCategory(callback: (ArrayList<CategoryDomain>) -> Unit) {
    val listCategory = ArrayList<CategoryDomain>()
    val dbReference = FirebaseDatabase.getInstance().getReference("Category")

    dbReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            listCategory.clear()
            if (snapshot.exists()) {
                for (category in snapshot.children) {
                    val cate = category.getValue(CategoryDomain::class.java)
                    listCategory.add(cate!!)
                }
            }
            callback(listCategory)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Failed to read value: ${error.toException()}")
        }
    })
}


}
