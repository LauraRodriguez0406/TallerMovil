package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONArray

class countrie1movil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countrie1movil)
        val countryName = intent.getStringExtra("countryName")

        val jsonString = getJsonFromAssets("paises.json")
        val jsonArray = JSONArray(jsonString)
        var capital = ""
        var info = ""
        var imageName = "default_country_image"

        for (i in 0 until jsonArray.length()) {
            val countryObject = jsonArray.getJSONObject(i)
            if (countryObject.getString("nombre_pais") == countryName) {
                capital = countryObject.getString("capital")
                info = "Capital: $capital\nNombre Internacional: ${countryObject.getString("nombre_pais_int")}\nSigla: ${countryObject.getString("sigla")}"
                imageName = countryObject.optString("imagen", "default_country_image")
                break
            }
        }

        val imageView = findViewById<ImageView>(R.id.imageView)
        val resourceId = resources.getIdentifier(imageName, "drawable", packageName)
        imageView.setImageResource(resourceId)

        val textViewCountryName = findViewById<TextView>(R.id.textViewCountryName)
        textViewCountryName.text = countryName

        val textViewCountryInfo = findViewById<TextView>(R.id.textViewCountryInfo)
        textViewCountryInfo.text = info
    }

    private fun getJsonFromAssets(fileName: String): String {
        val jsonString: String
        try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }
}