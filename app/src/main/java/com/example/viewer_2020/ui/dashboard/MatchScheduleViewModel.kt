/*
* MatchScheduleViewModel.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import androidx.lifecycle.ViewModel

//Fragment view handler for the MatchScheduleViewModel fragment.
//The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
class MatchScheduleViewModel : ViewModel() {
    var matchSchedule: HashMap<String, Match> = HashMap()

    fun loadMatchSchedule() {
        matchSchedule = convertMatchScheduleListToMap(csvFileRead("match_schedule.csv", false))
    }
}