package com.example.workforfirstsem.classes

import com.example.workforfirstsem.interfaces.AdminInterface

class Admin(nickname: String, login: String, password: String) : User(
    nickname,
    login,
    password
), AdminInterface {

    override fun addUser() {
        println("I add user")
    }

    override fun deleteUser() {
        println("I delete user")
    }

}
