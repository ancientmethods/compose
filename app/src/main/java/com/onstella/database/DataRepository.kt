package com.onstella.database

import androidx.lifecycle.LiveData
import com.onstella.database.entities.Coaches
import com.onstella.database.entities.LearnCategories
import com.onstella.database.entities.Items
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val dao:AppDao) {

    fun getCategories():List<LearnCategories>{
        return dao.getCategories()
    }

    fun getItems():List<Items>{
        return dao.getItems()
    }

    fun insertCategories(learnCategories: List<LearnCategories>){

        dao.insertCategories(learnCategories)
    }

    fun insertItems(items: List<Items>){
        dao.insertItems(items)
    }

    fun insertCoaches( items: ArrayList<Coaches>){
        dao.insertCoaches(items)
    }

    fun getCoaches():List<Coaches>{
        return dao.getCoaches()
    }
}