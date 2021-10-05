package com.example.workforfirstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import com.example.workforfirstsem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with (binding) {
            editButton.setOnClickListener {
                nameTextView.visibility = View.GONE
                editNameTextView.visibility = View.VISIBLE
                editNameTextView.setOnKeyListener(object : View.OnKeyListener {
                    override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                        if (event.action == KeyEvent.ACTION_DOWN &&
                            keyCode == KeyEvent.KEYCODE_ENTER
                        ) {
                            nameTextView.text = editNameTextView.text
                            nameTextView.visibility = View.VISIBLE
                            editNameTextView.visibility = View.GONE
                            return true
                        }
                        return false
                    }
                })
            }
        }
    }
}
