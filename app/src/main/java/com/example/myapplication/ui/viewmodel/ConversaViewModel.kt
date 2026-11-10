package com.example.myapplication.ui.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.auth.SessaoUsuario
import com.example.myapplication.data.dao.ConversaDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversaViewModel(application: Application): AndroidViewModel(application) {
    private val dao = ConversaDAO(application)
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
                SessaoUsuario.exigirUsuario()
                op()
            } catch (e: SQLiteConstraintException) {
                mensagem = "Não foi possivel cadastrar. Confira se o email já esta cadastrado"
            } catch (e: IllegalStateException) {
                mensagem = e.message
            } catch (e: Exception) {
                mensagem = "Ocorreu um erro inseperado -> $e"
            } finally {
                carregando = false
            }
        }
    }
}