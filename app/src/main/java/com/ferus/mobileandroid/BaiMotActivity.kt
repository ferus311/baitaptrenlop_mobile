package com.ferus.mobileandroid

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BaiMotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_1)

        val etNumber: EditText = findViewById(R.id.etNumber)
        val rbEven: RadioButton = findViewById(R.id.rbEven)
        val rbOdd: RadioButton = findViewById(R.id.rbOdd)
        val rbSquare: RadioButton = findViewById(R.id.rbSquare)
        val btnShow: Button = findViewById(R.id.btnShow)
        val listView: ListView = findViewById(R.id.listView)
        val tvError: TextView = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            val input = etNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập một số nguyên dương"
                tvError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập một số nguyên dương hợp lệ"
                tvError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            tvError.visibility = TextView.GONE
            val result = when {
                rbEven.isChecked -> getEvenNumbers(n)
                rbOdd.isChecked -> getOddNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                else -> listOf()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
