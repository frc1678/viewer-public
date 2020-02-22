package com.example.viewer_2020

import com.example.viewer_2020.constants.Constants

fun convertToFilteredTeamsList(path: String, filter: String, teamsList: List<String>): List<String> {
    val unsortedMap = HashMap<String, Double>()
    for (team in teamsList) {
        unsortedMap[team] = if (getTeamObjectByKey(path, team, filter) != Constants.NULL_CHARACTER)
            getTeamObjectByKey(path, team, filter).toDouble()
        else 1000.0
    }
    return unsortedMap.toList().sortedBy { (_, value) -> value}.toMap().keys.toList()
}