package com.example.workforfirstsem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workforfirstsem.databinding.ActivityMainBinding
import com.example.workforfirstsem.model.AppDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
                    .replace(R.id.container, ListTodoFragment())
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListTodoFragment())
            .commit()
    }
}
