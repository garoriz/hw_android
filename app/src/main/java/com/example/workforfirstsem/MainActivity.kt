package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.workforfirstsem.databinding.ActivityMainBinding
import com.example.workforfirstsem.model.AppDatabase
import com.example.workforfirstsem.model.entity.Todo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listTodoFragment = ListTodoFragment()

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        db = AppDatabase(this)

        with(binding) {
            btnDeleteAll.setOnClickListener {
                db.todoDao().deleteAll()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, listTodoFragment)
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, listTodoFragment)
            .commit()
    }

    override fun onBackPressed() {
        if (listTodoFragment.isResumed)
            super.onBackPressed()
        else
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, listTodoFragment)
                .commit()

    }
}
