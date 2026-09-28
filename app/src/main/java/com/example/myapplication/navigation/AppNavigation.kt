package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.ChatScreen
import com.example.myapplication.ui.screens.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Main
    ) {

        composable<Chat> {
            ChatScreen(
                voltar = {
                    navController.popBackStack()
                }
            )
        }

        composable<Main> {
            MainScreen(
                irParaChat = {
                    navController.navigate(Chat)
                }
            )
        }

    }
}