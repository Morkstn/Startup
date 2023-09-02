package com.fiap.startup.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nome: String = "",
    var email: String= "",
    var cpf: String = "",
    var saldo: Double = 0.00,
    var bonus: Double = 0.00,
    var premium: Boolean = false,
    var password: String = "",

)
