package com.bentes.controlejornada.ui.screens.jornada

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//ViewModel → controla estado

data class JornadaState(
    val trabalhando: Boolean = false,
    val pausado: Boolean = false,
    val folga: Boolean = false
)
class JornadaViewModel : ViewModel(){
    private val _state = MutableStateFlow(JornadaState()) // "_status" essa variável é interna (privada) e mutável
    val state: StateFlow<JornadaState> = _state

    fun iniciarOuFinallizarJornada(){
        val atual = _state.value
        if(!atual.trabalhando){
            _state.value = atual.copy(
                trabalhando = true,
                folga = false
            )
        }else{
            _state.value = atual.copy(
                trabalhando = false,
                pausado = false
            )
        }
    }
    fun alternarPausa(){
        val atual = _state.value
        if(atual.trabalhando){
            _state.value = atual.copy(
                pausado = !atual.pausado
            )
        }
    }
    fun marcarFolga(){
        _state.value = JornadaState(folga = true)
    }
}