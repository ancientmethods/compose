package com.onstella.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = LearnCategories::class, parentColumns = arrayOf("id"), childColumns = arrayOf("category"), onDelete = ForeignKey.CASCADE)])
data class Items(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(index = true)
    var category: Int,
    var title: String,
    var duration:String,
    var filter:String

)