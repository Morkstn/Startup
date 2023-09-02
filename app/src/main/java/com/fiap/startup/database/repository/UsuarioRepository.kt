package com.fiap.startup.database.repository

import android.content.Context
import com.fiap.startup.database.dao.UsuarioDb
import com.fiap.startup.model.Usuario

class UsuarioRepository(context: Context) {

    var db = UsuarioDb.getDatabase(context).usuarioDao()

    fun salvar(usuario: Usuario): Long{
        return db.salvar(usuario = usuario)
    }
    fun atualizar(usuario: Usuario): Int{
        return db.atualizar(usuario = usuario)
    }
    fun excluir(usuario: Usuario): Int{
        return db.excluir(usuario = usuario)
    }
    fun consultaSaldo(id: Long): Double {
        return db.consultaSaldo(id)
    }

}