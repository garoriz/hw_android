package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workforfirstsem.classes.Admin
import com.example.workforfirstsem.classes.Moderator
import com.example.workforfirstsem.classes.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = User("user123", "test@test.ru", "qwerty")
        user.printName()
        val admin = Admin("good_admin", "admin@test.ru", "bad_admin")
        admin.printName()
        admin.addUser()
        admin.deleteUser()
        val moder = Moderator("moder", "moder@test.ru", "1111")
        moder.printName()
        moder.changeComment()
    }
}
