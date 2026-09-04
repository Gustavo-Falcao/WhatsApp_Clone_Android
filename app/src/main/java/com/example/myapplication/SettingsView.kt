package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.GrayTextForeGround
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenMessageBoxSender
import com.example.myapplication.ui.theme.GreenPrimary
import com.example.myapplication.ui.theme.MainBackGroundColor
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SettingsView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackGroundColor)
    ) {
        SettingsHeader()
        SettingsBody()
    }
}

@Composable
fun SettingsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreenBackGroundNavBar)
            .padding(vertical = 15.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_icon),
                contentDescription = "Arrow back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Profile",
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = "Search icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SettingsBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        PhotoField()
        OptionSettings()
    }
}

@Composable
fun PhotoField() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ){
        Box(){
            Icon(
                painter = painterResource(id = R.drawable.baseline_person),
                contentDescription = "Person Icon",
                tint = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = GreenPrimary)
                    .padding(30.dp)
                    .size(100.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.photo_camera_icon),
                contentDescription = "Arrow back",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(50.dp))
                    .background(color = GreenMessageBoxSender)
                    .padding(15.dp)
                    .size(23.dp)
            )
        }
    }
}

@Composable
fun OptionSettings() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(start = 10.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 17.sp,
            color = GrayTextForeGround
        )
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.key_icon),
                    contentDescription = "Key icon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Account",
                        color = Color.White
                    )
                    Text(
                        text = "Security notifications, change number",
                        fontSize = 13.sp,
                        color = GrayTextForeGround,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                    SeparatorLine()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lock_icon),
                    contentDescription = "Lock Incon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Privacy",
                        color = Color.White
                    )
                    Text(
                        text = "Block contacts, disappearing messages",
                        fontSize = 13.sp,
                        color = GrayTextForeGround,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                    SeparatorLine()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.account_circle_icon),
                    contentDescription = "Person icon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Avatar",
                        color = Color.White
                    )
                    Text(
                        text = "Create, edit, profile photo",
                        fontSize = 13.sp,
                        color = GrayTextForeGround,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                    SeparatorLine()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.contacts_list_icon),
                    contentDescription = "List Group icon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Lists",
                        color = Color.White
                    )
                    Text(
                        text = "Manage people and groups",
                        fontSize = 13.sp,
                        color = GrayTextForeGround,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                    SeparatorLine()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = "Chat icon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Chats",
                        color = Color.White
                    )
                    Text(
                        text = "Theme, wallpapers, chat history",
                        fontSize = 13.sp,
                        color = GrayTextForeGround,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                    SeparatorLine()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications_icon),
                    contentDescription = "Notifications icon",
                    tint = Color.White,
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .size(24.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Notifications",
                        color = Color.White
                    )
                    Text(
                        text = "Message, group & call tones",
                        color = GrayTextForeGround,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SeparatorLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GrayTextForeGround)
            .padding(vertical = 0.5.dp)
    ) {}
}

@Preview(showBackground = true)
@Composable
fun SettingsViewPreview() {
    MyApplicationTheme {
        SettingsView()
    }
}