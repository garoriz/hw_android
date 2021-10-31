package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = (supportFragmentManager.findFragmentById(R.id.container)
                as NavHostFragment).navController
        setContentView(R.layout.activity_main)
    }
}
