package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.CadastroScreen
import com.example.myapplication.ui.screens.ChatScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Login
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

        composable<Login> {
            LoginScreen(
                irCadastro = {
                    navController.navigate(Cadastro)
                },
                irHome = {
                    navController.navigate(Main)
                }
            )
        }

        composable<Cadastro> {
            CadastroScreen(
                irLogin = {
                    navController.navigate(Login)
                }
            )
        }

    }
}