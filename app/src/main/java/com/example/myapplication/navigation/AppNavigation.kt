package com.example.myapplication.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.screens.CadastroScreen
import com.example.myapplication.ui.screens.ChatScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val entradaAtual by navController.currentBackStackEntryAsState()
    val destino = entradaAtual?.destination

    NavHost(
        navController = navController,
        startDestination = Main
    ) {

        composable<Chat>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(900)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(900)
                )
            }
        ) {
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
                    navController.popBackStack()
                }
            )
        }

    }
}