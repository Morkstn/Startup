package com.fiap.startup.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fiap.startup.model.Usuario
@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuario: Usuario): Long

    @Update
    fun atualizar(usuario: Usuario): Int

    @Delete
    fun excluir(usuario: Usuario): Int

    @Query("SELECT saldo FROM tb_usuario WHERE id = :id")
    fun consultaSaldo(id: Long): Double

    @Query("SELECT * FROM tb_usuario WHERE email = :email")
    fun buscarPorEmail(email: String): Usuario?
}