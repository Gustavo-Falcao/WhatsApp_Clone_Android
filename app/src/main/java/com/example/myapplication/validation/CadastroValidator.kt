package com.example.myapplication.validation

import android.util.Patterns

object CadastroValidator {
    fun validar(nome: String, telefone: String, senha: String) {
        require(nome.length in 3 .. 100) {
            "Informe um nome de até 100 caracteres"
        }

        require(telefone.length <= 11 &&
                Patterns
                    .PHONE
                    .matcher(telefone)
                    .matches()) {
            "Informe um telefone válido"
        }

        require(senha.length in 8 .. 100) {
            "Informe uma senha valida"
        }
    }
}