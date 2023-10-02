package com.example.superspiel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superspiel.composables.pager.MainPager
import com.example.superspiel.global.AppViewModel
import com.example.superspiel.network.NetworkService
import com.example.superspiel.ui.config.ScreenSize
import com.example.superspiel.ui.theme.SuperSpielTheme

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkService.closeWebClient()
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    App()
}

@Composable
fun App() {
    SuperSpielTheme {
        Main()
    }
}

@Preview(showBackground = true)
@Composable
fun Main(appViewModel: AppViewModel = viewModel()) {

    val screenSize = ScreenSize(LocalConfiguration.current, LocalDensity.current)
    appViewModel.screenSize = screenSize
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomBar() }
        ) { innerPadding ->
            MainPager(
                viewModel = appViewModel,
                innerPadding = innerPadding,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}

@Composable
fun TopBar(

) {
    Surface(
        tonalElevation = 6.dp,
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            
        ) {
            Text(text = "Top bar", style = MaterialTheme.typography.displaySmall)
        }
    }
}
@Composable
fun BottomBar(

) {
    Surface(
        tonalElevation = 6.dp,
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            FloatingActionButton(
                modifier = Modifier
                    .size(60.dp),
                onClick = {  },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Refresh, "Reload")
            }
        }


    }
}







