package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.intellij.lang.annotations.Language

class ramdom1movil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramdom1movil)

        val hola = findViewById<TextView>(R.id.hola)

        val selectedLanguage = intent.getStringExtra("language")
        val mostrarMensaje = mostrarMensaje(selectedLanguage)
        hola.text = mostrarMensaje
    }

    private fun mostrarMensaje(language: String?): String{
        return when (language){
            "Español" -> "Hola, es un gusto que estés aquí"
            "Inglés" -> "Hi, it's nice you're here"
            "Mandarin" -> "您好，很高兴您来到这里。"
            "Francés" -> "Bonjour, c'est un plaisir que vous soyez ici."
            "Italiano" -> "Ciao, è un piacere che tu sia qui."
            "Alemán" -> "Hallo, es ist mir eine Freude, dass Sie hier sind."
            else -> "Hola, Hi, 您好, Bonjour, Ciao, Hallo"
        }
    }
}