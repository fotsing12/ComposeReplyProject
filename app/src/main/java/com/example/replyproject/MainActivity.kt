package com.example.replyproject

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.replyproject.ui.theme.ReplyProjectTheme


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector
)

data class MessageItem(
    val senderProfileImageName: String,
    val senderName: String,
    val minutesPassed: Int,
    val favoriteIcon: Unit,
    val textMessage: String
)


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(activity = this)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MyApp(activity: ComponentActivity) {
    val windowSize = calculateWindowSizeClass(activity)
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                PhoneView()
            }
            WindowWidthSizeClass.Medium -> {
                Text("Tablet Layout")
            }
            WindowWidthSizeClass.Expanded -> {
                Text("Desktop Layout")
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneView(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val items = listOf(
        BottomNavigationItem(
            title = "Inbox",
            selectedIcon = Icons.Filled.Home,
        ),
        BottomNavigationItem(
            title = "Articles",
            selectedIcon = Icons.Sharp.Settings,
        ),
        BottomNavigationItem(
            title = "Inbox",
            selectedIcon = Icons.Filled.Home,
        ),
        BottomNavigationItem(
            title = "Articles",
            selectedIcon = Icons.Filled.Home,
        )
    )

    val messageItems = listOf(
        MessageItem(
            senderProfileImageName = "luffy",
            senderName = "Weber",
            minutesPassed = 20,
            favoriteIcon = Icon(
                painter = painterResource(id = R.drawable.luffy),
                contentDescription = "luffy",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            ),
            textMessage = "Hello how are you doing?"

        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar ={
            NavigationBar {
                items.forEachIndexed{ index, item ->
                    NavigationBarItem(
                        selected = index == 0,
                        enabled = true,
                        onClick = {
                            // navController.navigate(item.title)
                        },
                        label = {
                            Text(
                                text = item.title
                            )
                        },
                        icon = {
                            Icon(item.selectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                containerColor = Color.Cyan,
                onClick = {}
            ){
                Icon(Icons.Default.Edit, contentDescription = "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
        ) {
            SearchBar(
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                placeholder = { Text("search replies") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.luffy),
                        contentDescription = "luffy",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)

                    )
                },
                onActiveChange = {},
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                enabled = true,
            ){

            }
            messageItems.forEach { messageItem ->
                Surface(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.LightGray
                ) {

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReplyProjectTheme {

    }
}