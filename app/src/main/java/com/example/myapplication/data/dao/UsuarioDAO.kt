package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.Usuario
import com.example.myapplication.data.local.AppDatabase

internal class SenhaSalva(val hash: ByteArray, val salt: ByteArray)

internal class UsuarioDAO(context: Context) {
    private val  appContext = context.applicationContext

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

    fun buscarSenha(teleone: String): SenhaSalva? {
        return AppDatabase(appContext).use { db ->
            db.readableDatabase.query(
                "usuarios",
                arrayOf("senha", "salt"),
                "telefone = ?",
                arrayOf(teleone),
                null, null, null
            ).use { usuario ->
                if (usuario.moveToFirst()) SenhaSalva(
                    usuario.getBlob(0),
                    usuario.getBlob(1))
                else null
            }
        }
    }

    fun listar(): List<Usuario> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "usuarios", arrayOf("id", "nome", "telefone"),
            null, null, null, null, "nome COLLATE NOCASE, id"
        ).use { usuario ->
            buildList {
                while (usuario.moveToNext()) {
                    add(Usuario(
                        usuario.getLong(0),
                        usuario.getString(1),
                        usuario.getString(2)
                    ))
                }
            }
        }
    }
}