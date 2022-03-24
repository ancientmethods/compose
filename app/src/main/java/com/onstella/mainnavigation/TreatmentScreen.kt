package com.onstella.mainnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.onstella.AppBar
import com.onstella.R
import com.onstella.database.entities.Items
import com.onstella.ui.theme.LightBlue
import com.onstella.ui.theme.TextBody
import com.onstella.ui.theme.TextHeader
import com.onstella.ui.theme.TextSubtitle
import com.onstella.viewmodels.TreatmentViewMdel
import kotlinx.coroutines.delay

@Composable
fun TreatmentScreen(
    navController: NavController?,
    treatmentViewMdel: TreatmentViewMdel,
    category: String
) {

    //insert data into db
    var learnItems = arrayListOf<Items>()
    learnItems.add(Items(1, 1, stringResource(R.string.mindful_1), "5m", "Anxiety"))
    learnItems.add(Items(2, 1, stringResource(R.string.mindful_2), "3m", "Depression"))
    learnItems.add(Items(3, 1, stringResource(R.string.mindful_3), "7m", "Brain health"))
    treatmentViewMdel.insertItems(learnItems)
    Scaffold(topBar = {
        AppBar(
            title = category,
            icon = Icons.Default.ArrowBack
        ) { navController?.popBackStack() }
    }) {
        //display the selected category
        category?.let {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = it, style = TextHeader

                )
            }
        }

        //draw cards
        TreatmentScreenUI(treatmentViewMdel.listOfItems.value)


    }


    //load the treatment items
    LaunchedEffect(true) {
        treatmentViewMdel.getItems()

    }

}


@Composable
fun TreatmentScreenUI(items: List<Items>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        HorizontalScrollableComponent(items)
    }
}

@Composable
fun HorizontalScrollableComponent(treatmentList: List<Items>) {

    Spacer(modifier = Modifier.height(40.dp))
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                state = scrollState,
            ),
        content = {
            // We iterate over each item from the personList and define what each item should
            // look like.
            for (treatment in treatmentList) {

                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(140.dp)
                        .width(260.dp)

                ) {

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            treatment.title,
                            style = TextBody
                        )

                        Text(
                            text = treatment.filter.uppercase(),
                            style = TextSubtitle,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(color = LightBlue, shape = RectangleShape)
                                .padding(8.dp)
                        )
                    }

                }
            }
        })
}

@Preview
@Composable
fun TreatMentScreenPreview() {

    //val treatmentViewMdel: TreatmentViewMdel = hiltViewModel()
    var learnItems = arrayListOf<Items>()
    learnItems.add(Items(1, 1, stringResource(R.string.mindful_1), "5m", "Anxiety"))
    learnItems.add(Items(2, 1, stringResource(R.string.mindful_2), "3m", "Depression"))
    learnItems.add(Items(3, 1, stringResource(R.string.mindful_3), "7m", "Brain health"))
    TreatmentScreenUI(learnItems)

}

@Preview
@Composable
fun cardPreview() {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .height(140.dp)
            .width(260.dp)

    ) {


    }
}

