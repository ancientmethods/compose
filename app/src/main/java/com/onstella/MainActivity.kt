package com.onstella

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.onstella.database.entities.Items
import com.onstella.database.entities.LearnCategories
import com.onstella.mainnavigation.CoachScreen
import com.onstella.mainnavigation.LearnScreen
import com.onstella.mainnavigation.ProgressScreen
import com.onstella.mainnavigation.TreatmentScreen
import com.onstella.ui.theme.OnstellaTheme
import com.onstella.ui.theme.TextHeader
import com.onstella.viewmodels.CoachViewModel
import com.onstella.viewmodels.LearnViewModel
import com.onstella.viewmodels.TreatmentViewMdel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val learnViewModel: LearnViewModel by viewModels()
    private val treatmentViewMdel: TreatmentViewMdel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFakeData()
        //treatmentViewMdel.getItems()

        setContent {
            OnstellaTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(

                        bottomBar = {


                            BottomNavigation(
                                backgroundColor = Color.White, contentColor = colorResource(
                                    id = R.color.navy
                                )
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                navigation_items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            if (screen.route == Screen.Coach.route) {
                                                Image(

                                                    painterResource(R.drawable.coachicon),
                                                    "coach", contentScale = ContentScale.Crop
                                                )

                                            } else {
                                                Icon(screen.icon, contentDescription = null)
                                            }
                                        },
                                        label = { Text(stringResource(screen.resourceId)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        unselectedContentColor = colorResource(id = R.color.grey),
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.Coach.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Coach.route) {
                                var coachViewMdel:CoachViewModel = hiltViewModel()
                                CoachScreen(
                                    navController,
                                    coachViewMdel
                                )
                            }
                            composable(Screen.Learn.route) {
                                LearnScreen(
                                    navController,
                                    learnViewModel
                                )
                            }
                            composable(Screen.Plan.route) { PlanScreen() }
                            composable(Screen.Progress.route) { ProgressScreen() }
                            composable(Screen.Sos.route) { SosScreen() }
                            composable(Screen.Treatment.route) {
                                //val factory = HiltViewModelFactory(context = LocalContext.current, navBackStackEntry = it )
                                val treatmentViewMdel: TreatmentViewMdel = hiltViewModel()
                                TreatmentScreen(
                                    navController,
                                    treatmentViewMdel
                                )
                            }
                        }
                    }


                }

            }
        }
    }

    private fun addFakeData() {
        var learnCategories = arrayListOf<LearnCategories>()
        learnCategories.add(LearnCategories(1, getString(R.string.learn)))
        learnViewModel.insertCategories(learnCategories)



    }
}

val navigation_items = listOf(
    Screen.Plan,
    Screen.Learn,
    Screen.Coach,
    Screen.Sos,
    Screen.Progress,
)

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Coach : Screen("coach", R.string.coach, Icons.Filled.Person)
    object Learn : Screen("learn", R.string.learn, Icons.Filled.Favorite)
    object Progress : Screen("progress", R.string.progress, Icons.Filled.List)
    object Plan : Screen("plan", R.string.plan, Icons.Filled.Home)
    object Sos : Screen("sos", R.string.sos, Icons.Filled.Create)
    object Treatment : Screen("learn/treatment", R.string.treatmentlibraries, Icons.Filled.Home)


}






@Composable
fun PlanScreen() {


}

@Composable
fun SosScreen() {

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnstellaTheme {

    }
}










