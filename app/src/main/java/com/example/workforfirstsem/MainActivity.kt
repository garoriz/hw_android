package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.workforfirstsem.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val controller = (supportFragmentManager.findFragmentById(R.id.container)
                as NavHostFragment).navController

        val navView = binding.navView

        navView.setupWithNavController(controller)
    }
}
