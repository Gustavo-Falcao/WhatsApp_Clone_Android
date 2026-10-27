package com.example.myapplication.ui.screens.sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.FormNovoContato
import com.example.myapplication.navigation.NovoChat
import com.example.myapplication.ui.screens.FormNovoContatoScreen
import com.example.myapplication.ui.screens.NovoChatScreen
import com.example.myapplication.ui.theme.SheetBackGround

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(onDismiss: () -> Unit) {
    val sheetNavController = rememberNavController()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = SheetBackGround
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {

            NavHost(
                navController = sheetNavController,
                startDestination = NovoChat,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                composable<NovoChat> {
                    NovoChatScreen(
                        irParaForm = { sheetNavController.navigate(FormNovoContato) },
                        onDismiss = onDismiss
                    )
                }

                composable<FormNovoContato> {
                    FormNovoContatoScreen(
                        voltar = { sheetNavController.popBackStack() }
                    )
                }
            }
        }
    }
}