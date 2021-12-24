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
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listTodoFragment = ListTodoFragment()

    private lateinit var db: AppDatabase

    private lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        db = AppDatabase(this)

        scope = CoroutineScope(Dispatchers.Main + Job())

        with(binding) {
            btnDeleteAll.setOnClickListener {
                scope.launch {
                    db.todoDao().deleteAll()
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ListTodoFragment())
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, listTodoFragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
