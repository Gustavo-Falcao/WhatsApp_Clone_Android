package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.local.AppDatabase

internal class MensagemDAO(context: Context) {
    private val appContext = context.applicationContext
    private val conversaDao = ConversaDAO(context)

    fun cadastrar(usuarioAtualId: Long, destinatarioId: Long, conteudo: String) {

        AppDatabase(appContext).use { helper ->
            val db = helper.writableDatabase

            db.beginTransaction()

            try {
                val agora = System.currentTimeMillis()
                val conversaId = conversaDao.buscarOuCriarConversa(
                    db,
                    usuarioAtualId,
                    destinatarioId,
                    agora
                )
                val dados = ContentValues().apply {
                    put("conversa_id", conversaId)
                    put("remetente_id", usuarioAtualId)
                    put("conteudo", conteudo)
                    put("data_envio", agora)
                }
                db.insertOrThrow(
                    "mensagens",
                    null,
                    dados
                )

                conversaDao.alterarPorId(db, conversaId, agora)

                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }

    //marcar mensagem como lida
    fun marcarMensagemComoLida(conversaId: Long, usuarioAtualId: Long) {
        AppDatabase(appContext).use { db ->
            val valores = ContentValues().apply {
                put("status", "LIDO")
            }
            val alterado = db.writableDatabase.update(
                "mensagens",
                valores,
                "conversa_id = ? AND remetente_id != ? AND status != ?",
                arrayOf(conversaId.toString(), usuarioAtualId.toString(), "LIDO")
            )
            require(alterado == 1) {"Mensagem não encontrada"}
        }
    }
}