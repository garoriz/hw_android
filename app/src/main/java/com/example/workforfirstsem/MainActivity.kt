package com.example.workforfirstsem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.workforfirstsem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentContainerView, HomeFragment())
            commit()
        }

        with (binding) {
            homeButton.setOnClickListener {
                val homeFragment = HomeFragment.getInstance()
                changeFragment(homeFragment)
            }

            userButton.setOnClickListener {
                val userFragment = UserFragment.getInstance()
                changeFragment(userFragment)
            }

            settingsButton.setOnClickListener {
                val settingsFragment = SettingsFragment.getInstance()
                changeFragment(settingsFragment)
            }
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack("fragment")
            commit()
        }
    }
}
