package gruzdev.artem.marvelapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import gruzdev.artem.marvelapp.screens.NavGraphs

@Composable
fun AppHost() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}
