package com.onstella.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Categories table to store the different treatment categories for the user
 */
@Entity(tableName = "Category")
data class LearnCategories(
    @PrimaryKey
    var id: Int,
    var name: String?

)

