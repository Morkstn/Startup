package com.fiap.startup.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class usuarioTeste : ViewModel() {

    //var nomeUsuarioLogado by mutableStateOf("")
    // Add a property to store the logged-in user's name
    var nomeUsuarioLogado: String by mutableStateOf("")
    var nomeUsuario by mutableStateOf("")
    var emailUsuario by mutableStateOf("")
    var senhaUsuario by mutableStateOf("")
    var cpf by mutableStateOf("")

}