package com.bentes.controlejornada.ui.screens.jornada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

// UI → chama ViewModel

@Composable
fun JornadaScreen(viewModel: JornadaViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    var horaAtual by remember { mutableStateOf(getHoraAtual()) }
    LaunchedEffect(Unit) {
        while (true) {
            horaAtual = getHoraAtual()
            delay(1000)
        }
    }
    val statusTexto = when{
        state.folga -> "Dia de folga"
        state.pausado ->"Em pausa"
        state.trabalhando -> "Trabalhando"
        else -> "Parado"
    }
    Column(                                     // Alinhamento geral dos componentes
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){                            // Posição dos componentes
        Text(getDataAtual())
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = horaAtual,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(statusTexto)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.iniciarOuFinallizarJornada() },
            modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if(state.trabalhando)"Finalizar Jornada"
                    else "Iniciar Jornada"
                )
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = {viewModel.alternarPausa()},
            modifier = Modifier.fillMaxWidth(),
            enabled = state.trabalhando
        ) {
            Text(
                if (state.pausado) "Finalizar Pausa"
                else "Iniciar Pausa"
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = { viewModel.marcarFolga() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dia de Folga")
        }
    }

}

// 🔧 Utils simples (depois a gente move pra utils/)
fun getHoraAtual(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}

fun getDataAtual(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date())
}
