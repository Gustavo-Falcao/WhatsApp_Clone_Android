package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.data.Conversa
import com.example.myapplication.data.local.AppDatabase
import java.time.Instant

internal class ConversaDAO(context: Context) {
    private val appContext = context.applicationContext

    fun cadastrar(usuario1Id: Long, usuario2Id: Long): Long? {
        var idConversa: Long? = null
        AppDatabase(appContext).use { db ->
            val dados = ContentValues().apply {
                put("usuario_1_id", usuario1Id)
                put("usuario_2_id", usuario2Id)
                put("ultima_atualizacao", System.currentTimeMillis())
            }
            idConversa = db.writableDatabase.insertOrThrow(
                "conversas",
                null,
                dados
            )
        }
        return idConversa
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

    fun buscarIdConversaExistente(db: SQLiteDatabase, user1: Long, user2: Long): Long? {
        var conversaIdEncontrada: Long? = null

        db.query(
            "conversas",
            arrayOf("id"),
            "(usuario_1_id = ? AND usuario_2_id = ?) OR (usuario_2_id = ? AND usuario_1_id = ?)",
            arrayOf(user1.toString(), user2.toString(), user2.toString(), user1.toString()),
            null,
            null,
            null
        ).use { cursor ->
            if(cursor.moveToFirst()) {
                conversaIdEncontrada = cursor.getLong(0)
            }
        }

        return conversaIdEncontrada
    }
}