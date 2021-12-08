package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workforfirstsem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listSongsFragment = ListSongsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListSongsFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (listSongsFragment.isResumed)
            super.onBackPressed()
        else
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, listSongsFragment)
                .commit()

    }
}
