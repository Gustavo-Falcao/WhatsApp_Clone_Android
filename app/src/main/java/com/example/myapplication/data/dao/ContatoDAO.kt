package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.Contato
import com.example.myapplication.data.local.AppDatabase

internal class ContatoDAO(context: Context) {
    private val appContext = context.applicationContext

    fun cadastrar(remetenteId: String, destinatarioId: String, conteudo: String, savedName: String) {
        AppDatabase(appContext).use { db ->
            val dados = ContentValues().apply {
                put("remetente_id", remetenteId)
                put("destinatario_id", destinatarioId)
                put("conteudo", conteudo)
            }
            db.writableDatabase.insertOrThrow(
                "mensagens",
                null,
                dados
            )
        }
    }

    fun listar(): List<Contato> = AppDatabase(appContext).use { db ->
        db.readableDatabase.query(
            "mensagens", arrayOf("id", "remetente_id", "destinatario_id", "conteudo", "data_envio"),
            null, null, null, null, null
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(Contato(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        cursor.getString(3)
                    ))
                }
            }
        }
    }

    fun excluir(id: Long, usuarioAtualId: Long) {
        require(id != usuarioAtualId) {"Você não pode excluir o seu usuário"}
        AppDatabase(appContext).use { db ->
            val excluido = db.writableDatabase.delete(
                "contatos", "id = ?",
                arrayOf(id.toString())
            )
            require(excluido == 1) {"Usuario não encontrado, Atualize a lista"}
        }
    }
}