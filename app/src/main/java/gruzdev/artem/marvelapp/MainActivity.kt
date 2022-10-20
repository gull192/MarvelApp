package gruzdev.artem.marvelapp

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gruzdev.artem.marvelapp.core.navigation.Screens
import gruzdev.artem.marvelapp.screens.persom_screen.PersonScreen
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonScreen
import gruzdev.artem.marvelapp.ui.theme.MarvelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = Screens.SelectPersonScreen.name) {
//                    composable(Screens.SelectPersonScreen.name) {
//                        SelectPersonScreen()
//                    }
//                    composable(Screens.PersonDetailsScreen.name) {
//                        navController.previousBackStackEntry?.arguments.getParcelable<>()
//                        PersonScreen()
//                    }
//                }
                SelectPersonScreen()
            }
        }
    }
}




