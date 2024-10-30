package com.ferus.mobileandroid
import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class AddressHelper(context: Context) {
    private val data: JSONObject

    init {
        val inputStream = context.resources.openRawResource(R.raw.tree)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        data = JSONObject(stringBuilder.toString())
    }

    fun getProvinces(): List<String> {
        val list = mutableListOf<String>()
        val keys = data.keys()
        for (key in keys) {
            list.add(data.getJSONObject(key).getString("name"))
        }
        return list
    }

    fun getDistricts(province: String): List<String> {
        val list = mutableListOf<String>()
        var jProvince: JSONObject? = null

        val keys = data.keys()
        for (key in keys) {
            if (data.getJSONObject(key).getString("name") == province) {
                jProvince = data.getJSONObject(key)
                break
            }
        }

        if (jProvince != null) {
            val jDistricts = jProvince.getJSONObject("quan-huyen")
            val districtKeys = jDistricts.keys()
            for (key in districtKeys) {
                list.add(jDistricts.getJSONObject(key).getString("name"))
            }
        }

        return list
    }

    fun getWards(province: String, district: String): List<String> {
        val list = mutableListOf<String>()
        var jProvince: JSONObject? = null
        var jDistrict: JSONObject? = null

        val keys = data.keys()
        for (key in keys) {
            if (data.getJSONObject(key).getString("name") == province) {
                jProvince = data.getJSONObject(key)
                break
            }
        }

        if (jProvince != null) {
            val jDistricts = jProvince.getJSONObject("quan-huyen")
            val districtKeys = jDistricts.keys()
            for (key in districtKeys) {
                if (jDistricts.getJSONObject(key).getString("name") == district) {
                    jDistrict = jDistricts.getJSONObject(key)
                    break
                }
            }
        }

        if (jDistrict != null) {
            val jWards = jDistrict.getJSONObject("xa-phuong")
            val wardKeys = jWards.keys()
            for (key in wardKeys) {
                list.add(jWards.getJSONObject(key).getString("name"))
            }
        }

        return list
    }
}
