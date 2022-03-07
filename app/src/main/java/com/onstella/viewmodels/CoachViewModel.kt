package com.onstella.viewmodels

import androidx.compose.runtime.*
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
class CoachViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {


    //different ways of getting the data andd getting state here
  //  var listOfCoaches = mutableStateListOf<Coaches>()
  //  var listOfCoaches1 = arrayListOf<Coaches>()

    private val _listOfCoaches: MutableState<List<Coaches>> = mutableStateOf(emptyList())
    val listOfDish: State<List<Coaches>> = _listOfCoaches

    var listOfItems = mutableStateListOf<Items>()
    var onLoad= mutableStateOf(false)


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

        if(!onLoad.value) {
            viewModelScope.launch(Dispatchers.IO) {
                //different ways to make the list a state. both work
                //listOfCoaches = dataRepository.getCoaches().toMutableStateList()
                _listOfCoaches.value =  dataRepository.getCoaches()
                onLoad.value = true
            }
        }

    }





}