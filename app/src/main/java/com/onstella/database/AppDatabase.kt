package com.onstella.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onstella.database.entities.Coaches
import com.onstella.database.entities.LearnCategories
import com.onstella.database.entities.Items

@Database(version = 3, entities = [LearnCategories::class, Items::class, Coaches::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}