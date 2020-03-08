/*
* getTeamSpecificMatchNumbers.kt
* viewer
*
* Created on 2/23/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.example.viewer_2020.constants.Constants

// Returns a filtered list of matches for the given team number.
fun getTeamSpecificMatchNumbers(teamNumber: String): List<String> {
    val matches = mutableListOf<String>()
    val sortedMatches: ArrayList<Int> = ArrayList()
    val sortedMatchListString: ArrayList<String> = ArrayList()
    for (`object` in getDirectField(MainViewerActivity.databaseReference!!.processed,
        Constants.PROCESSED_OBJECT.CALCULATED_OBJECTIVE_TEAM_IN_MATCH.value)
            as Array<*>) {
        if (getDirectField(`object`!!, "team_number").toString() == teamNumber) {
            if (getDirectField(`object`, "match_number").toString() !in matches) {
                matches.add(getDirectField(`object`, "match_number").toString())
                sortedMatches.add(getDirectField(`object`, "match_number").toString().toInt())
            }
        }
    }
    sortedMatches.sort()
    sortedMatches.forEach {
        sortedMatchListString.add(it.toString())
    }
    return sortedMatchListString
}

