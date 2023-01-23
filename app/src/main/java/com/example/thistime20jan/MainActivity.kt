package com.example.thistime20jan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_click_me = findViewById(R.id.PDF) as Button
        val btn = findViewById(R.id.image) as Button
        btn_click_me.setOnClickListener {
            supportFragmentManager.commit {
                replace<Pdfselect>(R.id.fragmentContainerView4)
                setReorderingAllowed(true)
                addToBackStack("name1")
            }
        }
        btn.setOnClickListener {
            supportFragmentManager.commit {
                replace<Image_select>(R.id.fragmentContainerView4)
                setReorderingAllowed(true)
                addToBackStack("name2")
            }
        }
    }
}