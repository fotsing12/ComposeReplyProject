package com.example.replyproject

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.replyproject.ui.theme.ReplyProjectTheme
import kotlinx.coroutines.launch


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector
)

data class MessageItem(
    val senderProfileImageName: String,
    val senderName: String,
    val minutesPassed: Int,
    val textMessage: String
)

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
        selectedIcon = Icons.Filled.Email,
    ),
    BottomNavigationItem(
        title = "Articles",
        selectedIcon = Icons.Filled.DateRange,
    )
)

val messageItems = listOf(
    MessageItem(
        senderProfileImageName = "luffy",
        senderName = "Weber",
        minutesPassed = 20,
        textMessage = "Hello how are you doing?"
    ),
    MessageItem(
        senderProfileImageName = "avengers",
        senderName = "Avengers",
        minutesPassed = 30,
        textMessage = "I am an Avenger "
    ),
    MessageItem(
        senderProfileImageName = "deku",
        senderName = "Deku",
        minutesPassed = 40,
        textMessage = "Hope you are a Hero"
    ),
    MessageItem(
        senderProfileImageName = "dice",
        senderName = "Dice",
        minutesPassed = 50,
        textMessage = "Like playing Dice, Join us"
    ),
    MessageItem(
        senderProfileImageName = "trafalgar",
        senderName = "Trafalgar",
        minutesPassed = 55,
        textMessage = "Hello how are you doing?"
    )
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

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MyApp(activity: ComponentActivity) {
    val windowSize = calculateWindowSizeClass(activity)
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                PhoneView()
            }
            WindowWidthSizeClass.Medium -> {
                TabletView()
            }
            WindowWidthSizeClass.Expanded -> {
                DesktopView()
            }
        }
}

@Composable
fun DesktopView(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(0xFFF2F0F4))
                        .padding(20.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, bottom = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Text(text = "REPLY",
                            color = Color.Blue
                        )
                        IconButton(
                            modifier  = Modifier.padding(start = 180.dp),
                            onClick = {
                            }
                        ){
                            Icon(Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(30.dp))
                        }
                    }

                    ExtendedFloatingActionButton(
                        onClick = {},
                        containerColor = Color(0xFFFFD6f7),
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        IconButton(
                            onClick = {}
                        ){
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                        Text(text = "Compose",
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )
                    }
                    Spacer(modifier = Modifier
                        .height(120.dp)
                    )
                    items.forEachIndexed{ index, item ->
                        Spacer(modifier = Modifier
                            .height(30.dp)
                        )
                        NavigationDrawerItem(
                            modifier = Modifier.background(Color(0xFFF2F0F4)),
                            selected = index == 0,
                            onClick = {},
                            icon = {
                                Icon(item.selectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(40.dp)
                                )
                            },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        },
        modifier = Modifier

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ReplySection(screenSize = "extended", modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                ReplySection(screenSize = "extended", last = true)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletView(modifier: Modifier = Modifier) {
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
           ModalDrawerSheet(
               modifier = Modifier
           ) {
               Column(
                   modifier = Modifier
                       .background(Color(0xFFF2F0F4))
                       .padding(20.dp)
                       .fillMaxWidth()
                       .fillMaxHeight()
               ) {
                   Row(
                       modifier = Modifier
                           .padding(start = 20.dp, top = 20.dp, bottom = 30.dp),
                       verticalAlignment = Alignment.CenterVertically,

                   ) {
                       Text(text = "REPLY",
                           color = Color.Blue
                       )
                       IconButton(
                           modifier  = Modifier.padding(start = 180.dp),
                           onClick = {
                               scope.launch {
                                   drawerState.close()
                               }
                           }
                       ){
                           Icon(Icons.Default.Menu,
                               contentDescription = "Menu",
                               modifier = Modifier.size(30.dp))
                       }
                   }

                   ExtendedFloatingActionButton(
                       onClick = {},
                       containerColor = Color(0xFFFFD6f7),
                       modifier = Modifier
                           .fillMaxWidth()
                   ){
                       IconButton(
                           onClick = {}
                       ){
                           Icon(Icons.Default.Edit, contentDescription = "Edit")
                       }
                       Text(text = "Compose",
                           modifier = Modifier
                               .padding(start = 30.dp)
                       )
                   }
                   Spacer(modifier = Modifier
                       .height(120.dp)
                   )
                   items.forEachIndexed{ index, item ->
                       Spacer(modifier = Modifier
                           .height(30.dp)
                       )
                       NavigationDrawerItem(
                           modifier = Modifier.background(Color(0xFFF2F0F4)),
                           selected = index == 0,
                           onClick = {},
                           icon = {
                               Icon(item.selectedIcon,
                                   contentDescription = item.title,
                                   modifier = Modifier
                                       .size(40.dp)
                               )
                           },
                           label = { Text(item.title) }
                       )
                   }
               }
           }
        },
        modifier = Modifier

    ) {
        NavigationRail(
            modifier = Modifier
                .width(120.dp),
            containerColor = Color(0xFFF2F0F4),
            header = {
                IconButton(
                    modifier = Modifier
                        .size(70.dp),
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                ){
                    Icon(Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .padding(top = 30.dp)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
            ) {

                Spacer(modifier = Modifier
                    .height(30.dp)
                )

                LargeFloatingActionButton(
                    containerColor = Color(0xFFFFD6f7),
                    onClick = {}
                ){
                    Icon(Icons.Default.Edit, contentDescription = "Add", modifier = Modifier.size(30.dp))
                }

                Spacer(modifier = Modifier
                    .height(120.dp)
                )

                items.forEachIndexed{ index, item ->
                    Spacer(modifier = Modifier
                        .height(30.dp)
                    )
                    NavigationRailItem(
                        modifier = Modifier,
                        selected = index == 0,
                        onClick = {},
                        icon = {
                            Icon(item.selectedIcon,
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        },
                        enabled = true
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .padding(start = 120.dp)
        ) {
            ReplySection(screenSize = "medium")

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneView(modifier: Modifier = Modifier) {
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
                containerColor = Color(0xFFFFD6f7),
                onClick = {}
            ){
                Icon(Icons.Default.Edit, contentDescription = "Add")
            }
        }
    ) {
        ReplySection(screenSize = "compact")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplySection(modifier: Modifier = Modifier, screenSize: String, last: Boolean = false) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val fontSize = when(screenSize) {
        "medium" -> MaterialTheme.typography.titleMedium.fontSize
        "extended" -> MaterialTheme.typography.titleMedium.fontSize
        else -> null
    }
    val textMessageFontSize = when(screenSize) {
        "medium" -> MaterialTheme.typography.titleLarge.fontSize
        "extended" -> MaterialTheme.typography.titleLarge.fontSize
        else -> null
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 76.dp)
            .background(color = Color(0xFFF2F0F4))

    ) {
        if(!last){
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
        }
        else{
            Row(modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 13.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text("Package Shipped!",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier
                        .size(8.dp)
                    )
                    Text("7 messages",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                }
                Spacer(modifier = Modifier
                    .size(60.dp)
                )
                Icon(Icons.Default.MoreVert, contentDescription = "moreVert")
            }
        }
        messageItems.forEach { messageItem ->
            val heightDp = when (Pair(screenSize, last)) {
                Pair("compact", false) -> 160.dp
                Pair("medium", false) -> 260.dp
                Pair("extended", false) -> 260.dp
                Pair("extended", true ) -> 400.dp
                else -> null
            }
            Surface(
                modifier = Modifier
                    .then(heightDp?.let { Modifier.height(it) } ?: Modifier )
                    .padding(start = 20.dp, end = 20.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clickable { },
                shape = RoundedCornerShape(12.dp),
                color = if(!last){
                    Color(0xFFE2E1EC)
                } else{
                    Color.White
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        val context = LocalContext.current
                        val imageResId = context.resources.getIdentifier(messageItem.senderProfileImageName, "drawable", context.packageName)
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(
                                    when (screenSize) {
                                        "compact" -> 40.dp
                                        "medium" -> 60.dp
                                        "extended" -> 60.dp
                                        else -> 40.dp
                                    }
                                )
                                .clip(CircleShape)

                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                        ){
                            Text(
                                text = messageItem.senderName,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = fontSize?: TextUnit.Unspecified
                            )
                            Text(
                                text = "${messageItem.minutesPassed} mins ago",
                                fontWeight = FontWeight.Light,
                                fontSize = fontSize?: TextUnit.Unspecified
                            )
                        }
                        Box(modifier = Modifier
                            .size(
                                when (screenSize) {
                                    "compact" -> 45.dp
                                    "medium" -> 65.dp
                                    "extended" -> 65.dp
                                    else -> 45.dp
                                }
                            )
                            .clip(CircleShape)
                            .background(color = Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Outlined.Star,
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .size(when(screenSize) {
                                        "compact" -> 25.dp
                                        "medium" -> 35.dp
                                        "extended" -> 35.dp
                                        else -> 40.dp
                                    })
                            )
                        }
                    }
                    Text(
                        text = messageItem.textMessage,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 5.dp),
                        fontSize = textMessageFontSize?: TextUnit.Unspecified
                    )
                    if(last){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(40.dp),
                                colors = ButtonColors(
                                    containerColor = Color.LightGray, contentColor = Color.Black,
                                    disabledContainerColor = Color.LightGray,
                                    disabledContentColor = Color.Black,
                                )
                            ) {
                                Text("Reply")
                            }
                            Button(
                                onClick = {},
                                modifier = Modifier.width(180.dp).height(40.dp),
                                colors = ButtonColors(
                                    containerColor = Color.LightGray, contentColor = Color.Black,
                                    disabledContainerColor = Color.LightGray,
                                    disabledContentColor = Color.Black,
                                )
                            ) {
                                Text("Reply All")
                            }
                        }
                    }
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