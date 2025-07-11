package com.rexample.ropasci.ui.theme.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rexample.ropasci.model.Choice
import com.rexample.ropasci.ui.theme.ROPASCITheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rexample.ropasci.R
import com.rexample.ropasci.model.Status
import com.rexample.ropasci.ui.theme.Typography

@Composable
fun RopasciScreen(
    viewModel: RopasciViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {},
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScoreCard(
                userScore = state.userScore.toString(),
                comScore = state.comScore.toString(),
                drawScore = state.drawScore.toString(),
            )

            Spacer(modifier = Modifier.height(40.dp))

            ChoiceButton(
                onRockClick = {
                    viewModel.UserChoice(Choice.ROCK)
                    viewModel.ComChoice()
                },
                onScissorsClick = {
                    viewModel.UserChoice(Choice.SCISSORS)
                    viewModel.ComChoice()
                },
                onPaperCLick = {
                    viewModel.UserChoice(Choice.PAPER)
                    viewModel.ComChoice()
                }
            )

            if (state.showDialog) {
                CustomDialog(
                    status = when {
                        state.isUserWin -> Status.WIN
                        state.isUserLose -> Status.LOSE
                        else -> Status.DRAW
                    },
                    userChoiceString = state.userChoiceString,
                    comChoiceSring = state.comChoiceString,
                    onClick = { viewModel.dismissDialog() }
                )
            }

        }
    }
}


@Composable
fun ScoreCard(
    modifier: Modifier = Modifier,
    userScore: String,
    comScore: String,
    drawScore: String,
) {
    Card(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Score", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 12.dp), color = MaterialTheme.colorScheme.primary)
            ScoreText(
                userScore = userScore,
                comScore = comScore,
                drawScore = drawScore,
            )
        }
    }
}

@Composable
fun ScoreText(
    modifier: Modifier = Modifier,
    userScore: String,
    comScore: String,
    drawScore: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "YOU",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF51D256)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userScore,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp, end = 18.dp),
                )
                Text(text = "-", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = comScore,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 18.dp, end = 12.dp)
                )
            }

            Text(
                text = "COM",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFD25151)
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = drawScore,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = "DRAW",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFD2B651)
            )
        }
    }
}

@Composable
fun ChoiceButton(
    onRockClick: () -> Unit,
    onPaperCLick: () -> Unit,
    onScissorsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(
            onClick = { onRockClick() }, // Panggil fungsi dengan kurung kosong
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rock),
                contentDescription = "rock",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "ROCK",
                style = MaterialTheme.typography.titleMedium
            )
        }

        OutlinedButton(
            onClick = { onPaperCLick() }, // Panggil fungsi dengan kurung kosong
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.paper),
                contentDescription = "paper",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "PAPER",
                style = MaterialTheme.typography.titleMedium
            )
        }

        OutlinedButton(
            onClick = { onScissorsClick() }, // Panggil fungsi dengan kurung kosong
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.scissors),
                contentDescription = "scissors",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "SCISSORS",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CustomDialog(
    status: Status,
    userChoiceString: String,
    comChoiceSring: String,
    onClick: () -> Unit
) {
    Dialog(onDismissRequest = { onClick() }) {
        Card {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (status) {
                        Status.WIN -> "YOU WIN"
                        Status.LOSE -> "YOU LOSE"
                        Status.DRAW -> "DRAW"
                    },
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "(you) $userChoiceString - $comChoiceSring (com)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onClick() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "OK", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ROPASCITheme {
        RopasciScreen()
    }
}


