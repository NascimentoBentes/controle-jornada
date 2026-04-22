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
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun JornadaScreen() {

    var trabalhando by remember { mutableStateOf(false) }
    var pausado by remember { mutableStateOf(false) }
    var folga by remember { mutableStateOf(false) }

    var horaAtual by remember { mutableStateOf(getHoraAtual()) }

    // Atualiza hora a cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            horaAtual = getHoraAtual()
            delay(1000)
        }
    }

    val statusTexto = when {
        folga -> "Dia de folga"
        pausado -> "Em pausa"
        trabalhando -> "Trabalhando"
        else -> "Parado"
    }

    val statusCor = when {
        folga -> Color.Gray
        pausado -> Color(0xFFFFA000)
        trabalhando -> Color(0xFF4CAF50)
        else -> Color.Red
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = getDataAtual(),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = horaAtual,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = statusTexto,
                    color = statusCor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botão principal
        Button(
            onClick = {
                if (!trabalhando) {
                    trabalhando = true
                    folga = false
                } else {
                    trabalhando = false
                    pausado = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (trabalhando) "Finalizar Jornada" else "Iniciar Jornada")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botão pausa
        OutlinedButton(
            onClick = {
                if (trabalhando) {
                    pausado = !pausado
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = trabalhando
        ) {
            Text(if (pausado) "Finalizar Pausa" else "Iniciar Pausa")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botão folga
        OutlinedButton(
            onClick = {
                folga = true
                trabalhando = false
                pausado = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Marcar dia de folga")
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
