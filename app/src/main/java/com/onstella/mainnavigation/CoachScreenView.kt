package com.onstella.mainnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.onstella.database.entities.Coaches
import com.onstella.viewmodels.CoachViewModel
import com.onstella.viewmodels.LearnViewModel
import com.onstella.ui.theme.GenericCard
import kotlinx.coroutines.launch

@Composable
fun CoachScreen(navController: NavController, viewModel: CoachViewModel) {


    // var listOfCoaches = remember{mutableStateListOf<Coaches>()}
    LaunchedEffect(true) {

        viewModel.insertCoaches()

        viewModel.getCoaches()
    }
    val listOfCoaches by remember { viewModel.listOfDish }
    val coroutineScope = rememberCoroutineScope()

    var textToShow by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {scaffoldState.snackbarHostState},
        content = {})

    Column {
        for (coach in listOfCoaches) {
            GenericCard(coach.coachName, coach.coachGender, onClick = {
                //get the title
                textToShow = coach.coachName

                coroutineScope.launch {

                    var snackbarResult = snackbarHostState.showSnackbar(
                        textToShow,
                        actionLabel = "Undo",
                        SnackbarDuration.Short
                    )




                }
            })
            //Greeting(coach = coach)
        }
    }

    displaySnackBar(snackbarHostState)


}


@Composable
fun displaySnackBar(
    snackbarHostState: SnackbarHostState
) {

    SnackbarHost(hostState = snackbarHostState) { snackbarData ->
        Snackbar(
            modifier = Modifier.padding(8.dp), action = {} ,content = {
                Text(text = snackbarData.message, color = Color.White)
            })

    }
}


@Composable
fun Greeting(coach: Coaches) {


    Text(text = "Hello ${coach.coachName}, ${coach.coachAge}, ${coach.coachGender}")


}

