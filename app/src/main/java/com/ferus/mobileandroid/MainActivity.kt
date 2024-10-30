package com.ferus.mobileandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFirstScreen = findViewById<Button>(R.id.btnFirstScreen)
        val btnSecondScreen = findViewById<Button>(R.id.btnSecondScreen)
        val btnThirdScreen = findViewById<Button>(R.id.btnThirdScreen)

        btnFirstScreen.setOnClickListener {
            val intent = Intent(this, BaiMotActivity::class.java)
            startActivity(intent)
        }

        btnSecondScreen.setOnClickListener {
            val intent = Intent(this, BaiHaiActivity::class.java)
            startActivity(intent)
        }

        btnThirdScreen.setOnClickListener {
            val intent = Intent(this, BaiBaActivity::class.java)
            startActivity(intent)
        }
    }
}