package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.data.Conversa
import com.example.myapplication.data.local.AppDatabase
import java.time.Instant

internal class ConversaDAO(context: Context) {
    private val appContext = context.applicationContext

    fun buscarOuCriarConversa(db: SQLiteDatabase, usuarioAtualId: Long, destinatarioId: Long, agora: Long): Long {
        var conversaId = buscarIdConversaExistente(db,usuarioAtualId, destinatarioId)

        if(conversaId == null) {
            conversaId = cadastrar(db, usuarioAtualId, destinatarioId, agora)
        }

        return conversaId
    }

    fun buscarIdConversaExistente(db: SQLiteDatabase, user1Id: Long, user2Id: Long): Long? {
        return db.query(
            "conversas",
            arrayOf("id"),
            "(usuario_1_id = ? AND usuario_2_id = ?) OR (usuario_1_id = ? AND usuario_2_id = ?)",
            arrayOf(user1Id.toString(), user2Id.toString(), user2Id.toString(), user1Id.toString()),
            null, null, null
        ).use { cursor ->
            if(cursor.moveToFirst()) cursor.getLong(0) else null
        }
    }

    fun cadastrar(db: SQLiteDatabase, usuario1Id: Long, usuario2Id: Long, agora: Long): Long {
        var idConversa: Long

        val dados = ContentValues().apply {
            put("usuario_1_id", usuario1Id)
            put("usuario_2_id", usuario2Id)
            put("ultima_atualizacao", agora)
        }
        idConversa = db.insertOrThrow(
            "conversas",
            null,
            dados
        )

        return idConversa
    }

    fun alterarPorId(db: SQLiteDatabase, conversaId: Long, agora: Long) {
        val dadosConversa = ContentValues().apply {
            put("ultima_atualizacao", agora)
        }
        val linhasAlteradas = db.update(
            "conversas",
            dadosConversa,
            "id = ?",
            arrayOf(conversaId.toString())
        )

        require(linhasAlteradas == 1) { "Não foi possível atualizar conversa" }
    }

    fun listarConversasExistentesDoUsuarioAtual(usuarioAtualId: Long): List<Conversa> = AppDatabase(appContext).use { db ->
        //funcao exigirUsuario no viewModel trara o usuarioAtualId correto

        db.readableDatabase.query(
            "conversas",
            arrayOf("id", "usuario_1_id", "usuario_2_id", "ultima_atualizacao"),
            "usuario_1_id = ? OR usuario_2_id = ?",
            arrayOf(usuarioAtualId.toString()),
            null,
            null,
            "ultima_atualizacao DESC"
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    add(Conversa(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        Instant.ofEpochMilli(cursor.getLong(3))
                    ))
                }
            }
        }
    }
}