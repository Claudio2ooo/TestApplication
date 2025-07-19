package com.example.testapplication.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.enums.NavBarPage
import com.example.testapplication.enums.TestTag
import com.example.testapplication.model.ImageItem

class ComposeMainActivity : ComponentActivity() {

    private val viewModel: ComposeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainNavigation(viewModel)
            }
        }
    }
}

@Composable
fun MainNavigation(viewModel: ComposeViewModel) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == NavBarPage.HOME.name,
                    onClick = { navController.navigate(NavBarPage.HOME.name) },
                    label = { Text(NavBarPage.HOME.text) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    modifier = Modifier.testTag(TestTag.HOME.name)
                )
                NavigationBarItem(
                    selected = currentRoute == NavBarPage.STATS.name,
                    onClick = { navController.navigate(NavBarPage.STATS.name) },
                    label = { Text(NavBarPage.STATS.text) },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier.testTag(TestTag.STATS.name)
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavBarPage.HOME.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavBarPage.HOME.name) { ListScreen(viewModel) }
            composable(NavBarPage.STATS.name) { StatsScreen(viewModel) }
        }
    }
}

@Composable
fun StatsScreen(viewModel: ComposeViewModel) {
    val clickCount = viewModel.clickCount.collectAsState()
    val itemList = viewModel.itemList.collectAsState()
    val imageList = viewModel.imageList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = NavBarPage.STATS.text,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Numero di click: ${clickCount.value}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Text(
            "Elementi nella lista semplice: ${itemList.value.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { viewModel.resetSimpleList() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .testTag(TestTag.RESET_SIMPLE_BUTTON.name)

        ) {
            Text(
                "Svuota lista elementi",
                textAlign = TextAlign.Center
            )
        }

        Text(
            "Elementi nella lista immagini: ${imageList.value.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { viewModel.resetImageList() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .testTag(TestTag.RESET_IMAGE_BUTTON.name)
        ) {
            Text(
                "Svuota lista immagini",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ListScreen(viewModel: ComposeViewModel) {
    val clickIncrement = viewModel.clickIncrement.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Esempio di UI in Compose",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row {
            Button(
                onClick = { viewModel.onButtonClickSimpleItem() },
                modifier = Modifier.testTag(TestTag.SIMPLE_BUTTON_ADD.name)
            ) {
                Text(
                    "Aggiungi\n${clickIncrement.value} elementi",
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { viewModel.onButtonClickImageItem() },
                modifier = Modifier.testTag(TestTag.IMAGE_BUTTON_ADD.name)
            ) {
                Text(
                    "Aggiungi\n${clickIncrement.value} immagini",
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ItemList(viewModel)
    }
}

@Composable
private fun ItemList(viewModel: ComposeViewModel) {

    val items by viewModel.itemList.collectAsState()
    val images by viewModel.imageList.collectAsState()

    if (items.size > images.size) {
        SimpleItemList(items)
    } else {
        ImageItemList(images)
    }
}

@Composable
fun SimpleItemList(itemList: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ->
        items(itemList) { item ->
            Text(
                text = item,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImageItemList(imageList: List<ImageItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ->
        items(imageList) { item ->
            Image(
                painter = painterResource(id = item.drawableRes),
                contentDescription = "Image ${item.id}",
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            )
        }
    }
}
