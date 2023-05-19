package com.example.admincarefood.Domain

class OrderDomain(
    var Id: String ="",
    var IdUser:String= "",
    var Status:Int=0,
    var Address:String ?=null,
    var NumberPhone:String ?=null,
    var DateTime:String = "") {
}