package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.local.AppDatabase

internal class SenhaSalva(val hash: ByteArray, var salt: ByteArray)

internal class UsuarioDAO(context: Context) {
    private val appContext = context.applicationContext

    fun cadastrar(nome: String, telefone: String, senha: ByteArray, salt: ByteArray) {
        AppDatabase(appContext).use { db ->
            val dados = ContentValues().apply {
                put("nome", nome)
                put("telefone", telefone)
                put("senha", senha)
                put("salt", salt)
            }
            db.writableDatabase.insertOrThrow(
                "usuarios",
                null,
                dados
            )
        }
    }

    fun buscarSenha(telefone: String): SenhaSalva? {
        return AppDatabase(appContext).use { db ->
            db.readableDatabase.query(
                "usuarios",
                arrayOf("senha", "salt"),
                "telefone = ?",//where
                arrayOf(telefone),
                null, null, null
            ).use { cursor ->
                if(cursor.moveToFirst()) SenhaSalva(
                    cursor.getBlob(0),
                    cursor.getBlob(1)
                ) else null
            }
        }
    }
}