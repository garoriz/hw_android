package com.example.workforfirstsem.classes

import com.example.workforfirstsem.interfaces.ModeratorInterface

class Moderator(nickname: String, login: String, password: String) : User(
    nickname,
    login,
    password
), ModeratorInterface {

    override fun changeComment() {
        println("I change comment")
    }
}
