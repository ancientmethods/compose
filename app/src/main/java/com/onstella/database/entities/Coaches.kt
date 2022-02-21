package com.onstella.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Coaches")
data class Coaches(
    @PrimaryKey
    var coachName: String,
    var coachAge:String,
    var coachGender: String

)


