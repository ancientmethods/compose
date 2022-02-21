package com.onstella.mainnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.navigation.NavController
import com.onstella.R
import com.onstella.database.entities.Items
import com.onstella.ui.theme.LightBlue
import com.onstella.ui.theme.TextBody
import com.onstella.ui.theme.TextHeader
import com.onstella.ui.theme.TextSubtitle
import com.onstella.viewmodels.TreatmentViewMdel
import kotlinx.coroutines.delay

@Composable
fun TreatmentScreen(navController: NavController, treatmentViewMdel: TreatmentViewMdel) {

    //insert data into db
    var learnItems = arrayListOf<Items>()
    learnItems.add(Items(1, 1, stringResource(R.string.mindful_1), "5m", "Anxiety"))
    learnItems.add(Items(2, 1, stringResource(R.string.mindful_2), "3m", "Depression"))
    learnItems.add(Items(3, 1, stringResource(R.string.mindful_3), "7m", "Brain health"))
    treatmentViewMdel.insertItems(learnItems)

    LaunchedEffect(true) {
        treatmentViewMdel.getItems()

    }
    //draw cards

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = stringResource(id = R.string.treatmentlibraries), style = TextHeader,
            textAlign = TextAlign.Center
        )
        HorizontalScrollableComponent(treatmentViewMdel.listOfItems.value)
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

