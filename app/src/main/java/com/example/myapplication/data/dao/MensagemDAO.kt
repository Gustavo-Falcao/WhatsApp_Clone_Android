package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.local.AppDatabase

internal class MensagemDAO(context: Context) {
    private val appContext = context.applicationContext

    fun cadastrar(conversaId: Long, remetenteId: Long, conteudo: String) {
        AppDatabase(appContext).use { db ->
            val dados = ContentValues().apply {
                put("conversa_id", conversaId)
                put("remetente_id", remetenteId)
                put("conteudo", conteudo)
                put("data_envio", System.currentTimeMillis())
            }
            db.writableDatabase.insertOrThrow(
                "mensagens",
                null,
                dados
            )
        }
    }

    //marcar mensagem como lida
}