package com.example.myapplication.data

import java.time.Instant

data class Conversa(
    val id: Long,
    val usuario1Id: Long,
    val usuario2Id: Long,
    val ultimaAtualizacao: Instant//atualiza ao enviar mensagem
)