package com.rexample.ropasci.ui.theme.ui

import androidx.lifecycle.ViewModel
import com.rexample.ropasci.model.Choice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RopasciViewModel: ViewModel(){
    private val _state = MutableStateFlow(RopasciUiState())
    val state: StateFlow<RopasciUiState> = _state

    fun UserChoice(choice: Choice) {
        val stringChoice: String = if (choice == Choice.ROCK) "ROCK" else if (choice == Choice.PAPER) "PAPER" else "SCISSORS"
        _state.update {
            it.copy(userChoice = choice, userChoiceString = stringChoice)
        }
    }

    fun ComChoice() {
        val userChoice = _state.value.userChoice

        val comChoice: Choice

        val randomValue = (1..100).random()

        if (userChoice != null) {
            comChoice = when {
                randomValue <= 55 -> when (userChoice) {
                    Choice.ROCK -> Choice.PAPER
                    Choice.PAPER -> Choice.SCISSORS
                    Choice.SCISSORS -> Choice.ROCK
                }
                randomValue in 56..78 -> userChoice
                else -> when (userChoice) {
                    Choice.ROCK -> Choice.SCISSORS
                    Choice.PAPER -> Choice.ROCK
                    Choice.SCISSORS -> Choice.PAPER
                }
            }
        } else {
            comChoice = Choice.values().random()
        }


        _state.update {
            it.copy(
                comChoice = comChoice,
                comChoiceString = comChoice.name
            )
        }

        calculateResult(userChoice, comChoice)
    }

    private fun calculateResult(userChoice: Choice?, comChoice: Choice) {
        if (userChoice == null) return

        val isDraw = userChoice == comChoice
        val isUserWin = (userChoice == Choice.ROCK && comChoice == Choice.SCISSORS) ||
                (userChoice == Choice.PAPER && comChoice == Choice.ROCK) ||
                (userChoice == Choice.SCISSORS && comChoice == Choice.PAPER)

        val isUserLose = !isUserWin && !isDraw

        _state.update {
            it.copy(
                isDraw = isDraw,
                isUserWin = isUserWin,
                isUserLose = isUserLose,
                userScore = if (isUserWin) it.userScore + 1 else it.userScore,
                comScore = if (isUserLose) it.comScore + 1 else it.comScore,
                drawScore = if (isDraw) it.drawScore + 1 else it.drawScore,
                showDialog = true
            )
        }
    }

    fun dismissDialog() {
        _state.update {
            it.copy(showDialog = false)
        }
    }


}

data class RopasciUiState(
    val userChoiceString: String = "",
    val userChoice: Choice? = null,
    val comChoiceString: String = "",
    val comChoice: Choice? = null,
    val isUserWin: Boolean = false,
    val isUserLose: Boolean = false,
    val isDraw: Boolean = false,
    val userScore: Int = 0,
    val comScore: Int = 0,
    val drawScore: Int = 0,
    val showDialog: Boolean = false

)