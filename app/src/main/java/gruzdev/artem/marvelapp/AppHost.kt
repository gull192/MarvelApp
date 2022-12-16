package gruzdev.artem.marvelapp

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import gruzdev.artem.marvelapp.screens.NavGraphs

@Composable
fun AppHost() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}
