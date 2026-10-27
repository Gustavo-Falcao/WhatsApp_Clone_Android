package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.GrayBox
import com.example.myapplication.ui.theme.GrayTextForeGround
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenPrimary
import java.nio.file.WatchEvent

@Composable
fun NovoChatScreen(irParaForm: () -> Unit, onDismiss: () -> Unit) {
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        HeaderNovoChat(
            search,
            onSearchChange = { newValue -> search = newValue },
            onDismiss = onDismiss
        )

        ColumnCreateActions(irParaForm = irParaForm)
    }
}

@Composable
fun HeaderNovoChat(search: String, onSearchChange: (String) -> Unit, onDismiss: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New chat",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                IconButton(
                    onClick = onDismiss,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = GreenBackGroundNavBar
                    )
                ) {
                    Icon(
                        painterResource(id = R.drawable.close) ,
                        contentDescription = "Fechar",
                        tint = Color.White
                    )
                }
            }
        }

        TextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = {Text("Search")},
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            },
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
fun ColumnCreateActions(irParaForm: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14))
            .background(color = GrayBox)
            .padding(vertical = 14.dp, horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.group_add),
                contentDescription = "Criar grupo",
                tint = GreenPrimary
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text ="New group",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                SeparatorLine()
            }
        }

        Row(
            modifier = Modifier.clickable(
                onClick = irParaForm
            ),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.person_add),
                contentDescription = "Criar contato",
                tint = GreenPrimary
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "New contact",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                SeparatorLine()
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.groups_icon),
                contentDescription = "Criar comunidade",
                tint = GreenPrimary
            )
            Column() {
                Text(
                    text = "New community",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = "Bring together topic-based groups",
                    fontSize = 13.sp,
                    color = GrayTextForeGround,

                )
            }
        }


    }
}
