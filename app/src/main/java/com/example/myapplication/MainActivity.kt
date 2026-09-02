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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.myapplication.ui.theme.GrayBox
import com.example.myapplication.ui.theme.GrayTextForeGround
import com.example.myapplication.ui.theme.GreenSecondary
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 20.dp)
//            .background(color = Color.Red),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = modifier
//        )
//
//        Icon(
//            painter = painterResource(id = R.drawable.home_icon),
//            contentDescription = "Home Icon",
//            Modifier.size(40.dp)
//        )
//    }

    var search by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackGroundColor),

        verticalArrangement = Arrangement.spacedBy(20.dp)
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
                    fontSize = 30.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.photo_camera_icon),
                        contentDescription = "Photo camera icon",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.settings_icon),
                        contentDescription = "Settings icon",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
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
                        .padding(vertical = 10.dp)
                ) {
                    Text(text = "All", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = GrayBox)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 10.dp)

                ) {
                    Text(text = "Unread", color = GrayTextForeGround)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = GrayBox)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Text(text = "Groups", color = GrayTextForeGround)
                }
            }
        }

        Column() {
            //fazer for chamando contato element

            ContatoElement()
        }
        Row() { }
    }
}


@Composable
fun ContatoElement() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = GrayBox)
                    .padding(30.dp)
            ){}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Contato", color = Color.White)
                    Text(text = "Last message", color = GrayTextForeGround)
                }
                Text(text = "hh:mm", color = Color.White)

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}