package com.onstella.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun GenericCard(title:String, text:String , onClick:(String)->Unit){

    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .height(140.dp)
            .fillMaxWidth()
            .clickable {
                onClick(title)
            }

    ) {

        Column( modifier = Modifier
            .padding(16.dp)) {


            Text(
                title,
                style = TextBody
            )

            Text(
                text = text,
                style = TextSubtitle,
                modifier = Modifier
                    .background(color = LightBlue, shape = RectangleShape)
                    .padding(8.dp)
            )
        }

    }
}