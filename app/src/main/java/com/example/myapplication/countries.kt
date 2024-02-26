package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

class countries : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        val listView = findViewById<ListView>(R.id.listView)

        val countriesList = mutableListOf<String>()
        val jsonString = getJsonFromAssets("countries.json")

        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val countryObject = jsonArray.getJSONObject(i)
            countriesList.add(countryObject.getString("nombre_pais"))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countriesList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedCountryName = countriesList[position]
            val intent = Intent(this, countrie1movil::class.java)
            intent.putExtra("countryName", selectedCountryName)
            startActivity(intent)
        }
    }

    private fun getJsonFromAssets(fileName: String): String {
        val jsonString: String
        try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }
}