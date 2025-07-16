package com.example.testapplication.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.enums.NavBarPage
import kotlinx.coroutines.flow.compose

class MainActivity : ComponentActivity() {

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
                    selected = currentRoute == NavBarPage.home.name,
                    onClick = { navController.navigate(NavBarPage.home.name) },
                    label = { Text(NavBarPage.home.text) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentRoute == NavBarPage.stats.name,
                    onClick = { navController.navigate(NavBarPage.stats.name) },
                    label = { Text(NavBarPage.stats.text) },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavBarPage.home.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavBarPage.home.name) { ListScreen(viewModel) }
            composable(NavBarPage.stats.name) { StatsScreen(viewModel) }
        }
    }
}

@Composable
fun StatsScreen(viewModel: ComposeViewModel) {
    val clickCount = viewModel.clickCount.collectAsState()
    val itemList = viewModel.itemList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = NavBarPage.stats.text,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Numero di click: ${clickCount.value}", style = MaterialTheme.typography.bodyLarge)
        Text("Elementi nella lista: ${itemList.value.size}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ListScreen(viewModel: ComposeViewModel) {
    val clickIncrement = viewModel.clickIncrement.collectAsState()
    val itemList = viewModel.itemList.collectAsState()

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

        Button(
            onClick = { viewModel.onButtonClick() }
        ) {
            Text("Ogni click aggiunge\n${clickIncrement.value} elementi", textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(itemList.value) { item ->
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
}