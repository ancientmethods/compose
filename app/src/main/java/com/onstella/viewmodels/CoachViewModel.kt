package com.onstella.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onstella.database.DataRepository
import com.onstella.database.entities.Coaches
import com.onstella.database.entities.Items
import com.onstella.database.entities.LearnCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoachViewModel @Inject constructor(val dataRepository: DataRepository): ViewModel() {

    //var categories = mutableStateListOf(Categories)

    var listOfCoaches = mutableStateListOf<Coaches>()
    var listOfItems = mutableStateListOf<Items>()


    fun  insertCoaches(){

        var listOfCoaches = arrayListOf<Coaches>()
        listOfCoaches.add(
            Coaches("Amanda","24","female")
        )
        listOfCoaches.add(
            Coaches("Mike","32","male")
        )
        listOfCoaches.add(
            Coaches("Liam","24","male")
        )

        viewModelScope.launch(Dispatchers.IO){
            dataRepository.insertCoaches(listOfCoaches)

        }
    }

    fun getCoaches(){

        viewModelScope.launch(Dispatchers.IO){
            listOfCoaches = dataRepository.getCoaches().toMutableStateList()

        }

    }





}