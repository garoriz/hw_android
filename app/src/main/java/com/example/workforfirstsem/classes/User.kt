package com.example.workforfirstsem.classes

open class User(
    var nickname: String,
    var login: String,
    var password: String
) {

    fun printName() {
        println(nickname)
    }
}
