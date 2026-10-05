package com.example.myapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.myapplication.R
import com.example.myapplication.auth.AuthManager
import com.example.myapplication.ui.theme.GreenInputLine
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

            Text(text = "Sing up", fontSize = 24.sp, fontWeight = FontWeight.W500)

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.widthIn(max = 300.dp)) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = {nome = it},
                    label = {Text("Nome")},
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GreenInputLine
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefone,
                    onValueChange = {telefone = it},
                    label = {Text("Telefone")},
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.phone_icon),
                            contentDescription = "Phone icon",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GreenInputLine
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

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
                        containerColor = GreenPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }
            }

            Spacer(Modifier.height(10.dp))

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
        }

    }
}