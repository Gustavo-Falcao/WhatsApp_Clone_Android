package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.LineHeightStyle
import com.example.myapplication.ui.theme.BlueCheckMessage
import com.example.myapplication.ui.theme.GrayBox
import com.example.myapplication.ui.theme.GrayTextForeGround
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenPrimary
import com.example.myapplication.ui.theme.GreenSecondary
import com.example.myapplication.ui.theme.GreenShadow
import com.example.myapplication.ui.theme.MainBackGroundColor
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Conversas(modifier = Modifier.padding(innerPadding))
                    ChatView(modifier = Modifier.padding(innerPadding))
                    //SettingsView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Conversas(modifier: Modifier = Modifier) {
    var search by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(color = MainBackGroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "WhatsApp",
                    color = Color.White,
                    fontSize = 24.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.photo_camera_icon),
                        contentDescription = "Photo camera icon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.settings_icon),
                        contentDescription = "Settings icon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            TextField(
                value = search,
                onValueChange = {search = it},
                label = {Text("Search")},
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth(),
                singleLine = true
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = GreenSecondary)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 5.dp)
                ) {
                    Text(text = "All", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = GrayBox)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 5.dp)

                ) {
                    Text(text = "Unread", color = GrayTextForeGround)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = GrayBox)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 5.dp)
                ) {
                    Text(text = "Groups", color = GrayTextForeGround)
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            for (i in 1 .. 15) {
                val isNotificationViewd = i % 2 == 0
                val showSeparatorLine = i != 15
                ContatoElement(isNotificationViewd, showSeparatorLine)
            }
        }
        Box() {
            Icon(
                painter = painterResource(id = R.drawable.chat_add),
                contentDescription = "Chat icon",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(
                        x = (-10).dp,
                        y = (-70).dp
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = GreenPrimary)
                    .padding(18.dp)
                    .size(24.dp)
            )
            MenuBar()
        }
    }
}


@Composable
fun MenuBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreenBackGroundNavBar)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
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
            verticalArrangement = Arrangement.spacedBy(5.dp)
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

@Composable
fun ContatoElement(isNotificationViewd: Boolean, showSeparatorLine: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_person),
                contentDescription = "Person Icon",
                tint = GrayTextForeGround,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = GrayBox)
                    .padding(10.dp)
                    .size(35.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(
                    modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Column() {
                        Text(text = "Contato", color = Color.White)
                        Text(text = "Last message", color = GrayTextForeGround)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "hh:mm", color = GrayTextForeGround, fontSize = 14.sp)
                        Icon(
                            painter = painterResource(id = R.drawable.check_message_icon),
                            contentDescription = "Check icon",
                            tint = if (isNotificationViewd) BlueCheckMessage else GrayTextForeGround ,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                // se parametro passado for true box não será criada
                if(showSeparatorLine) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = GrayTextForeGround)
                            .padding(vertical = (0.5).dp)
                    ) {}
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Conversas()
    }
}