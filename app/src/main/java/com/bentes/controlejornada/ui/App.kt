package com.bentes.controlejornada.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bentes.controlejornada.ui.screens.jornada.JornadaScreen
import com.bentes.controlejornada.ui.theme.ControleJornadaTheme

@Composable  // ← Só isso, NÃO é class App : ComponentActivity()
fun App() {
    ControleJornadaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            JornadaScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}