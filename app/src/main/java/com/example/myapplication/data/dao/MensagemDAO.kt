package com.example.myapplication.data.dao

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.local.AppDatabase

internal class MensagemDAO(context: Context) {
    private val appContext = context.applicationContext
    private val conversaDao = ConversaDAO(context)

    fun cadastrar(conversaId: Long, remetenteId: Long, conteudo: String) {

        AppDatabase(appContext).use { db ->
            val dbEscrita = db.writableDatabase

//            dbEscrita.inTransaction {
//                var conversaId = conversaDao.buscarIdConversaExistente(dbEscrita, )
//            }
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