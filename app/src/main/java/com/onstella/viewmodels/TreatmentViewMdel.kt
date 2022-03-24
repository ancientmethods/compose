package com.onstella.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onstella.database.DataRepository
import com.onstella.database.entities.Items
import com.onstella.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreatmentViewMdel @Inject constructor(val dataRepository: DataRepository, val sharedpreferences:PreferencesManager): ViewModel() {


    var listOfItems : MutableState<List<Items>> = mutableStateOf(ArrayList())
    var onLoad= mutableStateOf(false)

    fun getItems() {

        if(!onLoad.value){
            viewModelScope.launch (Dispatchers.IO) {
                dataRepository.getItems().let {

                    listOfItems.value = it
                    onLoad.value = true
                }

            }
        }

    }

    fun insertItems(learnItems:List<Items>){
        viewModelScope.launch (Dispatchers.IO) {
            dataRepository.insertItems(learnItems)
        }
    }



}