/*
* MatchScheduleViewModel.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


//Fragment view handler for the MatchScheduleViewModel fragment.
//The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
//A ViewModel is not necessary for this display, yet it is used to maintain a constant structure.
class MatchScheduleViewModel : ViewModel() {
    private val matchSchedule: MutableLiveData<HashMap<String, Match>> = MutableLiveData()

    //Returns the updated match schedule.
    fun getMatchSchedule(): LiveData<HashMap<String, Match>> {
        loadMatchSchedule()
        return matchSchedule
    }

    //Pulls the match schedule from the internal storage of the tablet.
    private fun loadMatchSchedule() {
        matchSchedule.value = (convertMatchScheduleListToMap(csvFileRead("match_schedule.csv", false)))
    }

}