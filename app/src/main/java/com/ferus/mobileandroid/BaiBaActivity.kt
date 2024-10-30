package com.ferus.mobileandroid

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class BaiBaActivity : AppCompatActivity() {
    private lateinit var mssvEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var showDatePickerButton: Button
    private lateinit var selectedDateTextView: TextView
    private lateinit var wardSpinner: Spinner
    private lateinit var districtSpinner: Spinner
    private lateinit var provinceSpinner: Spinner
    private lateinit var sportsCheckBox: CheckBox
    private lateinit var moviesCheckBox: CheckBox
    private lateinit var musicCheckBox: CheckBox
    private lateinit var termsCheckBox: CheckBox
    private lateinit var submitButton: Button

    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_3)

        mssvEditText = findViewById(R.id.mssvEditText)
        nameEditText = findViewById(R.id.nameEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        showDatePickerButton = findViewById(R.id.showDatePickerButton)
        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        wardSpinner = findViewById(R.id.wardSpinner)
        districtSpinner = findViewById(R.id.districtSpinner)
        provinceSpinner = findViewById(R.id.provinceSpinner)
        sportsCheckBox = findViewById(R.id.sportsCheckBox)
        moviesCheckBox = findViewById(R.id.moviesCheckBox)
        musicCheckBox = findViewById(R.id.musicCheckBox)
        termsCheckBox = findViewById(R.id.termsCheckBox)
        submitButton = findViewById(R.id.submitButton)

        addressHelper = AddressHelper(this)

        setupSpinners()

        showDatePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        submitButton.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            selectedDateTextView.text = "Ngày sinh đã chọn: ${dateFormat.format(selectedDate.time)}"
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun setupSpinners() {
        val provinces = addressHelper.getProvinces()
        val provinceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = provinceAdapter

        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                val districts = addressHelper.getDistricts(selectedProvince)
                val districtAdapter = ArrayAdapter(this@BaiBaActivity, android.R.layout.simple_spinner_item, districts)
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                districtSpinner.adapter = districtAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinceSpinner.selectedItem.toString()
                val selectedDistrict = districtSpinner.selectedItem.toString()
                val wards = addressHelper.getWards(selectedProvince, selectedDistrict)
                val wardAdapter = ArrayAdapter(this@BaiBaActivity, android.R.layout.simple_spinner_item, wards)
                wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                wardSpinner.adapter = wardAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
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
