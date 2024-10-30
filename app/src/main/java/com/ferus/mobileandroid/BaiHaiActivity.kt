package com.ferus.mobileandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class BaiHaiActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_2)

        recyclerView = findViewById(R.id.recyclerView)
        val searchEditText: EditText = findViewById(R.id.searchEditText)

        studentList = mutableListOf(
            Student("Tran Thi B", "654321"),
            Student("Le Van C", "234567"),
            Student("Pham Thi D", "765432"),
            Student("Hoang Van E", "345678"),
            Student("Vo Thi F", "876543"),
            Student("Bui Van G", "456789"),
            Student("Do Thi H", "987654"),
            Student("Pham Van I", "567890"),
            Student("Vu Thi J", "098765"),
            Student("Nguyen Van K", "112233"),
            Student("Tran Thi L", "223344"),
            Student("Le Van M", "334455"),
            Student("Pham Thi N", "445566"),
            Student("Hoang Van O", "556677"),
            Student("Vo Thi P", "667788"),
            Student("Bui Van Q", "778899"),
            Student("Do Thi R", "889900"),
            Student("Pham Van S", "990011"),
            Student("Vu Thi T", "101112"),
            Student("Nguyen Van U", "121314"),
            Student("Tran Thi V", "141516"),
            Student("Le Van W", "161718"),
            Student("Pham Thi X", "181920"),
            Student("Hoang Van Y", "202122"),
            Student("Vo Thi Z", "222324"),
            Student("Bui Van AA", "242526"),
            Student("Do Thi BB", "262728"),
            Student("Pham Van CC", "282930"),
            Student("Vu Thi DD", "303132"),
            Student("Nguyen Van EE", "323334"),
            Student("Tran Thi FF", "343536"),
            Student("Le Van GG", "363738"),
            Student("Pham Thi HH", "383940"),
            Student("Hoang Van II", "404142"),
            Student("Vo Thi JJ", "424344"),
            Student("Bui Van KK", "444546"),
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            studentList.filter {
                it.name.contains(text, ignoreCase = true) || it.mssv.contains(text, ignoreCase = true)
            }
        } else {
            studentList
        }
        studentAdapter.updateList(filteredList)
    }
}
