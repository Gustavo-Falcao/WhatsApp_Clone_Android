package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.auth.SessaoUsuario
import com.example.myapplication.data.Contato
import com.example.myapplication.data.local.AppDatabase

internal class ContatoDAO(context: Context) {
    private val appContext = context.applicationContext

    fun cadastrar(usuarioId: Long, usuarioContatoId: Long, nomeContato: String) {
        AppDatabase(appContext).use { db ->
            val dados = ContentValues().apply {
                put("usuario_id", usuarioId)
                put("usuario_contato_id", usuarioContatoId)
                put("nome", nomeContato)
            }
            db.writableDatabase.insertOrThrow(
                "contatos",
                null,
                dados
            )
        }
    }
    //listagem de contatos será por id do usuário atual e ordenando por nome
    //colocar clausula para pegar os contatos que tem o usuarioId igual ao do usuário atual
    fun listarPorUsuarioId(usuarioAtualId: Long): List<Contato> {
        return AppDatabase(appContext).use { db ->
            db.readableDatabase.query(
                "contatos",
                arrayOf("id", "usuario_id", "usuario_contato_id", "nome"),
                "id = ?",
                arrayOf(usuarioAtualId.toString()),
                null,
                null,
                "nome COLLATE NOCASE, id"
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
    }

    fun excluir(id: Long, usuarioAtualId: Long) {
        require(id != usuarioAtualId) {"Você não pode excluir o seu contato"}
        AppDatabase(appContext).use { db ->
            val excluido = db.writableDatabase.delete(
                "contatos", "id = ?",
                arrayOf(id.toString())
            )
            require(excluido == 1) {"Usuario não encontrado, Atualize a lista"}
        }
    }
}