package com.example.myapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.auth.AuthManager
import com.example.myapplication.ui.theme.GreenPrimary

@Composable
fun CadastroScreen(irLogin: () -> Unit) {
    var telefone by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to WhatsApp",
                fontSize = 30.sp
            )

            Spacer(Modifier.height(15.dp))

            Text(text = "Sing In", fontSize = 24.sp, fontWeight = FontWeight.W500)

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = {nome = it},
                label = {Text("Nome")},
                modifier = Modifier.widthIn(max = 200.dp)
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                value = telefone,
                onValueChange = {telefone = it},
                label = {Text("Telefone")},
                modifier = Modifier.widthIn(max = 200.dp)
            )

            Spacer(Modifier.height(5.dp))

            Row() {
                Text("Already have an account ?")
                Spacer(Modifier.width(5.dp))
                Text(
                    "Log In Here",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        onClick = irLogin
                    )
                )
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    if(nome.isBlank() || telefone.isBlank()) {
                        Toast.makeText(
                            context,
                            "Preencha todos so campos!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else {
                        if(AuthManager.cadastrar(nome, telefone)) {
                            irLogin()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    contentColor = Color.Black
                )
            ) {
                Text("Signin")
            }

        }

    }
}