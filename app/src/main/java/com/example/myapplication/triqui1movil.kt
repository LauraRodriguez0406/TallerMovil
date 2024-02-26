package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.graphics.Color
import android.widget.Button
import android.view.View
import androidx.annotation.NonNull
import java.sql.ClientInfoStatus


class triqui1movil : AppCompatActivity() {

    private var playerOneActive = false
    private lateinit var playerOneScore: TextView
    private lateinit var playerTwoScore: TextView
    private lateinit var playerStatus: TextView
    private lateinit var buttons: Array<Button>
    private lateinit var reset: Button
    private lateinit var playAgain: Button
    private val gameState = intArrayOf(2,2,2,2,2,2,2,2,2)
    private val winningPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )
    private var rounds = 0
    private var playerOneScoreCount = 0
    private var playerTwoScoreCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triqui1movil)

        playerOneScore = findViewById(R.id.score_Player1)
        playerTwoScore = findViewById(R.id.score_Player2)
        playerStatus = findViewById(R.id.textStatus)
        reset = findViewById(R.id.reset)
        playAgain = findViewById(R.id.playAgain)
        buttons = arrayOf(
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8)
        )

        buttons.forEach { it.setOnClickListener {
            // Verifica si el botón está vacío y no hay un ganador
            if ((it as Button).text.toString() == "" && !checkWinner()) {
                // Si el jugador activo es el jugador 1, coloca "X", sino "O"
                if (playerOneActive) {
                    it.text = "X"
                    it.setTextColor(Color.parseColor("#ffc34a"))
                    gameState[buttons.indexOf(it)] = 0 // Actualiza el estado del juego
                } else {
                    it.text = "O"
                    it.setTextColor(Color.parseColor("#70fc3a"))
                    gameState[buttons.indexOf(it)] = 1 // Actualiza el estado del juego
                }
                rounds++ // Incrementa el número de rondas jugadas

                // Verifica si hay un ganador después de colocar el símbolo en el botón
                if (checkWinner()) {
                    // Si hay un ganador, actualiza los puntajes y muestra el mensaje correspondiente
                    if (playerOneActive) {
                        playerOneScoreCount++
                        updatePlayerScore()
                        playerStatus.text = "El jugador-1 ha ganado"
                    } else {
                        playerTwoScoreCount++
                        updatePlayerScore()
                        playerStatus.text = "El jugador-2 ha ganado"
                    }
                } else if (rounds == 9) {
                    // Si no hay ganador y se han realizado 9 movimientos, muestra un mensaje de empate
                    playerStatus.text = "No hay ganador"
                } else {
                    // Cambia al siguiente jugador si no hay ganador y no se ha llegado al límite de movimientos
                    playerOneActive = !playerOneActive
                }
            }
        }}

        playerOneScoreCount = 0
        playerTwoScoreCount = 0
        playerOneActive = true
        rounds = 0

        reset.setOnClickListener {
            playAgain()
            playerOneScoreCount = 0
            playerTwoScoreCount = 0
            updatePlayerScore()
        }

        playAgain.setOnClickListener { playAgain() }
    }

    fun onClick(view: View) {
        if ((view as Button).text.toString() != "") {
            return
        } else if (checkWinner()) {
            return
        }

        val buttonID = resources.getResourceEntryName(view.id)
        val gameStatePointer = buttonID.substring(buttonID.length - 1).toInt()

        if (playerOneActive) {
            view.text = "X"
            view.setTextColor(Color.parseColor("#ffc34a"))
            gameState[gameStatePointer] = 0
        } else {
            view.text = "O"
            view.setTextColor(Color.parseColor("#70fc3a"))
            gameState[gameStatePointer] = 1
        }

        rounds++

        if (checkWinner()) {
            if (playerOneActive) {
                playerOneScoreCount++
                updatePlayerScore()
                playerStatus.text = "El jugador-1 ha ganado"
            } else {
                playerTwoScoreCount++
                updatePlayerScore()
                playerStatus.text = "El jugador-2 ha ganado"
            }
        } else if (rounds == 9) {
            playerStatus.text = "No hay ganador"
        } else {
            playerOneActive = !playerOneActive
        }
    }

    private fun checkWinner(): Boolean {
        var winnerResults = false
        for (winningPositions in winningPositions) {
            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                gameState[winningPositions[0]] != 2
            ) {
                winnerResults = true
            }
        }
        return winnerResults
    }

    private fun playAgain() {
        rounds = 0
        playerOneActive = true
        buttons.forEach { it.text = "" }
        gameState.indices.forEach { gameState[it] = 2 }
        playerStatus.text = "Estado"
    }

    private fun updatePlayerScore() {
        playerOneScore.text = playerOneScoreCount.toString()
        playerTwoScore.text = playerTwoScoreCount.toString()
    }
}