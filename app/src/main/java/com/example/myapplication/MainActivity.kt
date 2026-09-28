package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                AppNavigation(navController = navController)
                    //ChatsScreen(irParaChat = {}, modifier = Modifier.padding(innerPadding))
                    //Conversas(modifier = Modifier.padding(innerPadding))
                    //ChatView(modifier = Modifier.padding(innerPadding))
                    //SettingsView(modifier = Modifier.padding(innerPadding))
                    //Conversas(modifier = Modifier.padding(innerPadding))
                    //SettingsScreen(modifier = Modifier.padding(innerPadding))

            }
        }
    }
}

@Composable
fun Greeting() {
    Text(text = "Hello")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}