package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.GrayBox
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenPrimary

@Composable
fun FormNovoContatoScreen(voltar: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        HeaderForm(voltar = voltar)
        Column(
            modifier = Modifier.padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            NameColumn(
                firstName = firstName,
                onChangeFirstName = { newValue -> firstName = newValue },
                lastName = lastName,
                onChangeLastName = { newValue -> lastName = newValue }
            )
            PhoneRow(
                phoneNumber = phoneNumber,
                onChangePhoneNumber = { newValue -> phoneNumber = newValue }
            )
        }
    }
}

@Composable
fun HeaderForm(voltar: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = voltar,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = GreenBackGroundNavBar
            )
        ) {
            Icon(
                painterResource(id = R.drawable.arrow_back_icon),
                contentDescription = "Voltar",
                tint = Color.White
            )
        }

        Text(
            text = "New contact",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        IconButton(
            onClick = {},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = GreenPrimary
            ),
            enabled = true
        ) {
            Icon(
                painterResource(id = R.drawable.check),
                contentDescription = "Salvar",
                tint = Color.White
            )
        }
    }
}

@Composable
fun NameColumn(firstName: String, lastName: String, onChangeFirstName: (String) -> Unit, onChangeLastName: (String) -> Unit) {
    Column() {
        TextField(
            value = firstName,
            onValueChange = onChangeFirstName,
            placeholder = {Text("First name")},
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = GrayBox,
                unfocusedContainerColor = GrayBox,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = GreenPrimary
            ),

        )

        SeparatorLine()

        TextField(
            value = lastName,
            onValueChange = onChangeLastName,
            placeholder = {Text("Last name")},
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = GrayBox,
                unfocusedContainerColor = GrayBox,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = GreenPrimary
            )
        )
    }
}

@Composable
fun PhoneRow(phoneNumber: String, onChangePhoneNumber: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = GrayBox)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Text(
                text = "Mobile",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "+55",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        TextField(
            value = phoneNumber,
            onValueChange = onChangePhoneNumber,
            placeholder = {Text("Phone")},
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = GreenPrimary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

    }
}