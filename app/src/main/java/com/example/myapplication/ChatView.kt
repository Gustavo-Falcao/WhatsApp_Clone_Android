package com.example.myapplication

import android.graphics.fonts.Font
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BlueCheckMessage
import com.example.myapplication.ui.theme.GrayBox
import com.example.myapplication.ui.theme.GrayInfoBoxMessage
import com.example.myapplication.ui.theme.GrayTextForeGround
import com.example.myapplication.ui.theme.GreenBackGroundNavBar
import com.example.myapplication.ui.theme.GreenMessageBoxSender
import com.example.myapplication.ui.theme.MainBackGroundColor
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.nio.file.WatchEvent
import kotlin.math.max

@Composable
fun ChatView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackGroundColor)
    ) {
        HeaderChat()
        BodyChat(modifier = Modifier.weight(1f))
        BottomChat()
    }
}

@Composable
fun HeaderChat() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreenBackGroundNavBar)
            .padding(vertical = 15.dp)
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_icon),
                contentDescription = "Arrow back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(color = GrayTextForeGround)
                    .padding(10.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.account_circle_icon),
                    contentDescription = "Arrow back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column() {
                Text(
                    text = "Nome Contato",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Status",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.videocam_icon),
                contentDescription = "Video cam",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.phone_icon),
                contentDescription = "Phone call",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.settings_icon),
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun BottomChat() {
    var textMsg by remember() { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreenBackGroundNavBar)
            .padding(horizontal = 5.dp)
            .padding(top = 10.dp)
            .padding(bottom = 5.dp)
    ) {
        TextField(
            value = textMsg,
            onValueChange = {textMsg = it}
        )
    }
}

@Composable
fun BodyChat(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BadgeInfo("Today")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementSender(
                "Hey!! Are we still on for tonight?",
                "18:42",
                true
            )
            MessageElementSender(
                "Or did you fall asleep after work again 😂",
                "18:43",
                true
            )
        }
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .padding(start = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementReceiver("I AM AWAKE", "18:45")
            MessageElementReceiver("Barely", "18:45")
            MessageElementReceiver("Give me 20 mins to pretend I’m a functional human.", "18:45")
            MessageElementReceiver("Are we doing tacos or that new Thai place on 4th?", "18:45")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementSender(
                "Thai!! I’ve been thinking about pad see ew all day long.",
                "18:47",
                true
            )
            MessageElementSender(
                "Plus tacos on a Friday night means a 2-hour wait minimum.",
                "18:47",
                true
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementReceiver("Fair point.", "18:48")
            MessageElementReceiver("Wait, did you make a reservation?.", "18:50")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementSender(
                "Did you? 💀",
                "18:50",
                true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            MessageElementReceiver("Sam.", "18:50")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementSender(
                "Okay don't \"Sam\" me, you were supposed to handle food, I was on drinks duty!",
                "18:51",
                true
            )
            MessageElementSender(
                "Hold on, calling them right now.",
                "18:51",
                true
            )
            MessageElementSender(
                "Okay good news and bad news.",
                "18:54",
                true
            )
            MessageElementSender(
                "Good news: They have a table.",
                "18:54",
                true
            )
            MessageElementSender(
                "Bad news: It’s either at 9:15 PM or right now at 7:00 PM.",
                "18:54",
                true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementReceiver("SEVEN PM??", "18:55")
            MessageElementReceiver("I haven't even washed my face yet!!", "18:55")
            MessageElementReceiver(
                "Take the 7 PM. I'll throw on sweatpants that look like real pants and a jacket. Absolute speed run.",
                "18:55"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementSender(
                "Done, snagged the 7 PM!",
                "18:56",
                true
            )
            MessageElementSender(
                "See you there in 15. Don't forget your wallet this time.",
                "18:56",
                true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MessageElementReceiver("THAT WAS ONE TIME IN 2022.", "18:56")
            MessageElementReceiver("Leaving now! 🚗💨", "18:55")
        }
    }
}

@Composable
fun MessageElementSender(message: String, hour: String, isChecked: Boolean) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(color = GreenMessageBoxSender)
            .padding(horizontal = 10.dp)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = message,
            color = Color.White,
            lineHeight = 21.sp,
            modifier = Modifier
                .widthIn(max = 230.dp)
                .padding(bottom = 5.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = hour,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = GrayInfoBoxMessage
            )
            Icon(
                painter = painterResource(id = R.drawable.check_message_icon),
                contentDescription = "Settings",
                tint = if(isChecked) BlueCheckMessage else GrayTextForeGround,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}

@Composable
fun MessageElementReceiver(message: String, hour: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(color = GreenBackGroundNavBar)
            .padding(horizontal = 10.dp)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = message,
            color = Color.White,
            lineHeight = 21.sp,
            modifier = Modifier
                .widthIn(max = 230.dp)
                .padding(bottom = 5.dp)
        )

        Text(
            text = hour,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = GrayInfoBoxMessage
        )
    }
}

@Composable
fun BadgeInfo(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = GrayTextForeGround,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = GreenBackGroundNavBar)
                .padding(horizontal = 10.dp)
                .padding(vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatViewPreview() {
    MyApplicationTheme {
        ChatView()
    }
}