package com.onstella.mainnavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.onstella.R
import com.onstella.Screen
import com.onstella.ui.theme.TextBody
import com.onstella.ui.theme.TextHeader
import com.onstella.viewmodels.LearnViewModel

@OptIn(ExperimentalFoundationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
//@Preview(showBackground = true)
fun LearnScreen(navController: NavController, learnViewModel:LearnViewModel) {
    /*...*//*
    Button(onClick = {
        navController.navigate("coach")
    }) {
        Text(text = "Navigate back")
    }*/



    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize(), contentScale = ContentScale.Fit
            )


        }


        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                learnViewModel.getCategories()


                //get the category
                if(learnViewModel.listOfCategories.isNotEmpty()) {
                    learnViewModel.listOfCategories[0].name?.let { it1 ->
                        Text(
                            it1,
                            style = TextHeader,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }


            /**
             * array of categories to be displayed as cards
             */
            /**
             * array of categories to be displayed as cards
             */
            val categories = arrayOf(
                stringResource(id = R.string.recipes),
                stringResource(id = R.string.exercises),
                stringResource(id = R.string.cq),
                stringResource(id = R.string.stellalive),
                stringResource(id = R.string.treatmentlibraries),
                stringResource(id = R.string.symptomglossary),
            )


            /**
             * array of images
             */
            /**
             * array of images
             */
            var images = arrayOf(
                R.drawable.recipes,
                R.drawable.exercises,
                R.drawable.community_questions,
                R.drawable.stellalive,
                R.drawable.stellalive,
                R.drawable.treatmentlibrary,
                R.drawable.symptom_glossary
            )

            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp
                    )
                ) {
                    items(categories.size) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            onClick = {
                                navController.navigate(Screen.Treatment.route+"/${categories[it]}")
                            }
                        ) {
                            Column(
                                Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.7f),

                                    painter = painterResource(images[it]),
                                    contentDescription = "",
                                    alignment = Alignment.TopCenter,
                                    contentScale = ContentScale.FillHeight
                                )

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = categories[it], style = TextBody

                                    )

                                }
                            }


                        }
                    }

                }

            }
        }


    }
    /*...*/
}