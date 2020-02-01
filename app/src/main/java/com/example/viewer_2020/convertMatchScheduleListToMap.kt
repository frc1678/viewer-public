/*
* convertMatchScheduleListToMap.kt
* viewer
*
* Created on 1/30/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

val matchScheduleMap: HashMap<String, Match> = HashMap()

//Converts the match schedule literal list pulled from a csv into a key to value format.
fun convertMatchScheduleListToMap(matchScheduleList: MutableList<String>): HashMap<String, Match> {
    for (match in matchScheduleList) {
        val currentMatch = Match()
        for (index in 1..3) currentMatch.blueTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
        for (index in 4..6) currentMatch.redTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
        currentMatch.matchNumber = match.trim().split(" ")[0]
        matchScheduleMap[match.trim().split(" ")[0]] = currentMatch
    }
    return matchScheduleMap
}


