package com.onstella.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onstella.database.entities.LearnCategories
import com.onstella.database.entities.Items
import com.onstella.mainnavigation.Coaches

/**
 * data access object for database queries
 */
@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(learnCategories:List<LearnCategories>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems( items:List<Items>)

    @Query("Select * from Category")
    fun getCategories():List<LearnCategories>

    @Query("Select * from Items")
    fun getItems():List<Items>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoaches( items:ArrayList<Coaches>)

    @Query("Select * from Coaches")
    abstract fun getCoaches(): ArrayList<Coaches>
}