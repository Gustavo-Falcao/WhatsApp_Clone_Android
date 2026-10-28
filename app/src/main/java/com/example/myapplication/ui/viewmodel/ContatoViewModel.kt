package com.example.myapplication.ui.viewmodel

import android.adservices.adid.AdId
import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.dao.ContatoDAO
import com.example.myapplication.data.dao.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContatoViewModel(application: Application): AndroidViewModel(application) {
    private val dao = ContatoDAO(application)
    private val usuarioDAO = UsuarioDAO(application)
    var carregando by mutableStateOf(false)
        private set
    var mensagem by mutableStateOf<String?>(null)
        private set
    var concluido by mutableStateOf(false)
        private set

    private fun executar(op: suspend () -> Boolean) {
        if(carregando || concluido) return
        carregando = true
        mensagem = null

        viewModelScope.launch {
            try {
                concluido = withContext(Dispatchers.IO){op()}
                if(!concluido) mensagem = "Algo invalido"
            } catch (e: SQLiteConstraintException) {
                mensagem = "Não foi possivel cadastrar."
            } catch (e: IllegalStateException) {
                mensagem = e.message
            } catch (e: Exception) {
                mensagem = "Ocorreu um erro inseperado -> $e"
            } finally {
                carregando = false
            }
        }
    }

//    fun cadastrar(usuarioAtualId: Long, contatoUsuarioId: Long, savedName: String) = executar {
//        if (usuarioDAO.existsPorId(contatoUsuarioId)) {
//            return false
//        }
//
//        try {
//            dao.cadastrar(usuarioAtualId, contatoUsuarioId, )
//        }
//    }
}