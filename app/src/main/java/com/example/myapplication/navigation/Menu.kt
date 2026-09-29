package com.example.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenPrimary
import com.example.myapplication.ui.theme.GreenShadow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(
    navController: NavHostController
) {
    BottomAppBar(
//        actions = {
//            TextButton(onClick = {
//                navController.navigate(Chats)
//            }) {
//                Text("Chats")
//            }
//            TextButton(onClick = {
//                navController.navigate(Settings)
//            }) {
//                Text("Settings")
//            }
//        }
        containerColor = GreenBackGroundNavBar,
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .clickable(onClick = {navController.navigate(Chats)})
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat_icon),
                        contentDescription = "Chat icon",
                        tint = GreenPrimary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = GreenShadow)
                            .padding(10.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Chats",
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone_icon),
                        contentDescription = "Phone icon",
                        tint = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Calls",
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.groups_icon),
                        contentDescription = "Groups icon",
                        tint = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Communities",
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.
                        clickable(onClick = {navController.navigate(Settings)})
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.account_circle_icon),
                        contentDescription = "Account icon",
                        tint = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "You",
                        color = Color.White
                    )
                }
            }
        }
    )
//    TopAppBar(
//        title = {
//            Text("Minha aplicacao")
//        },
//        actions = {
//            TextButton(onClick = {
//                navController.navigate(Chats)
//            }) {
//                Text("Chats")
//            }
//            TextButton(onClick = {
//                navController.navigate(Settings)
//            }) {
//                Text("Settings")
//            }
//        }
//    )
}