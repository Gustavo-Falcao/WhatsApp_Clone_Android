package com.example.myapplication.data

import java.time.Instant

data class Mensagem(
    val id: Long,
    val conversaId: Long,
    val remetenteId: Long,
    val conteudo: String,
    val dataEnvio: Instant,
    val status: String// ENTREGUE, LIDO
)