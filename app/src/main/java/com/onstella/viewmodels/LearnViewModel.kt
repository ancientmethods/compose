package com.onstella.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onstella.database.DataRepository
import com.onstella.database.entities.LearnCategories
import com.onstella.database.entities.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(val dataRepository: DataRepository): ViewModel() {

    //var categories = mutableStateListOf(Categories)

    var listOfCategories = mutableStateListOf<LearnCategories>()
    var listOfItems = mutableStateListOf<Items>()

    fun getCategories(){
        viewModelScope.launch (Dispatchers.IO) {
            dataRepository.getCategories().let {
                listOfCategories.clear()
                listOfCategories.addAll(it)
            }

        }
    }

    fun getItems() {
        viewModelScope.launch (Dispatchers.IO) {
            dataRepository.getItems().let {
                listOfItems.clear()
                listOfItems.addAll(it)
            }

        }
    }

    fun insertItems(learnItems:List<Items>){

        viewModelScope.launch (Dispatchers.IO) {
            dataRepository.insertItems(learnItems)
        }
    }

    fun insertCategories(learnCategories: List<LearnCategories>){
        // launch the coroutine in the viewmodel in order to survive activity changes
        viewModelScope.launch (Dispatchers.IO) {
            dataRepository.insertCategories(learnCategories)
        }
    }

}