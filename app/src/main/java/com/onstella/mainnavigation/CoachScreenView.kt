package com.onstella.mainnavigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.onstella.database.entities.Coaches
import com.onstella.viewmodels.CoachViewModel
import com.onstella.viewmodels.LearnViewModel

@Composable
fun CoachScreen(navController: NavController, viewModel: CoachViewModel) {


   var listOfCoaches = remember{mutableStateListOf<Coaches>()}

    listOfCoaches = viewModel.listOfCoaches
    
    for (coach in listOfCoaches){
        Greeting(coach = coach)
    }
    

}
@Composable
fun Greeting(coach:Coaches){
    
    Text(text = "Hello ${coach.coachName}, ${coach.coachAge}, ${coach.coachGender}")
    
}

