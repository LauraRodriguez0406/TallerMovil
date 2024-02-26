package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.Spinner
import android.widget.Button
import android.widget.Space



import com.example.myapplication.R

class MainActivity @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatActivity() {

    private lateinit var idioma: Spinner
    private lateinit var ramdon: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Reaccion de los botones


        //Boton Tic tac
        val tictac = findViewById<Button>(R.id.tictac)
        tictac.setOnClickListener(){
            Log.i("AristiDevs", "Boton Pulsado")
            val intent = Intent(this, tictac::class.java)
            startActivity(intent)
        }


        //Boton Ramdon Imagen
        val ramdon = findViewById<Button>(R.id.ramdon)
        val idioma = findViewById<Spinner>(R.id.idioma)

        ramdon.setOnClickListener(){
            Log.i("AristiDevs", "Boton Pulsado")
            val selectedLanguage = idioma.selectedItem.toString()
            val intent1 = Intent(this, ramdom1movil::class.java)
            intent1.putExtra("language", selectedLanguage)
            startActivity(intent1)
        }

        //Boton Tic tac
        val countries = findViewById<Button>(R.id.contries)
        tictac.setOnClickListener(){
            Log.i("AristiDevs", "Boton Pulsado")
            val intent2 = Intent(this, countries::class.java)
            startActivity(intent2)
        }
    }
}