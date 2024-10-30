package com.ferus.mobileandroid

import android.os.Bundle

import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BaiBaActivity : AppCompatActivity() {
    private lateinit var mssvEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var showCalendarButton: Button
    private lateinit var calendarView: CalendarView
    private lateinit var wardSpinner: Spinner
    private lateinit var districtSpinner: Spinner
    private lateinit var provinceSpinner: Spinner
    private lateinit var sportsCheckBox: CheckBox
    private lateinit var moviesCheckBox: CheckBox
    private lateinit var musicCheckBox: CheckBox
    private lateinit var termsCheckBox: CheckBox
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_3)

        mssvEditText = findViewById(R.id.mssvEditText)
        nameEditText = findViewById(R.id.nameEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        showCalendarButton = findViewById(R.id.showCalendarButton)
        calendarView = findViewById(R.id.calendarView)
        wardSpinner = findViewById(R.id.wardSpinner)
        districtSpinner = findViewById(R.id.districtSpinner)
        provinceSpinner = findViewById(R.id.provinceSpinner)
        sportsCheckBox = findViewById(R.id.sportsCheckBox)
        moviesCheckBox = findViewById(R.id.moviesCheckBox)
        musicCheckBox = findViewById(R.id.musicCheckBox)
        termsCheckBox = findViewById(R.id.termsCheckBox)
        submitButton = findViewById(R.id.submitButton)

        showCalendarButton.setOnClickListener {
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
        }

        submitButton.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        if (mssvEditText.text.isEmpty()) {
            mssvEditText.error = "MSSV is required"
            return false
        }
        if (nameEditText.text.isEmpty()) {
            nameEditText.error = "Name is required"
            return false
        }
        if (genderRadioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Gender is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (emailEditText.text.isEmpty()) {
            emailEditText.error = "Email is required"
            return false
        }
        if (phoneEditText.text.isEmpty()) {
            phoneEditText.error = "Phone number is required"
            return false
        }
        if (!termsCheckBox.isChecked) {
            Toast.makeText(this, "You must agree to the terms", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
