package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.Chats
import com.example.myapplication.navigation.Menu
import com.example.myapplication.navigation.Settings

@Composable
fun MainScreen(
    irParaChat: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = { Menu(navController = navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Chats,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            composable<Chats> {
                ChatsScreen(irParaChat = irParaChat)
            }

            composable<Settings> {
                SettingsScreen(voltar = {
                    navController.popBackStack()
                })
            }
        }
    }
}