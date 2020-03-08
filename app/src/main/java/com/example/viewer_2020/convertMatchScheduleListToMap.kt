/*
* convertMatchScheduleListToMap.kt
* viewer
*
* Created on 1/30/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.example.viewer_2020.data.Match

val matchScheduleMap: HashMap<String, Match> = HashMap()

//Converts the match schedule literal list pulled from a csv into a key to value format.
fun convertMatchScheduleListToMap(
    matchScheduleList: MutableList<String>,
    isFiltered: Boolean,
    matchNumber: Int?
): HashMap<String, Match>? {
    // If isFiltered is true, only load the match details for the given match number.
    // Because of this, matchNumber should only contain a value if isFiltered is true, so if
    // isFiltered is true and matchNumber is inputted as null, then there is no need to continue
    // with the function as it won't be able to filter using a null match number when isFiltered is true.
    if (isFiltered && matchNumber == null) return null
    // Each item of the matchScheduleList list looks like the following:
    // Example: '1 B-1 B-2 B-3 R-1 R-2 R-3
    // Splitting each string by the space creates a list of elements that allows for
    // the individual recognition of each team and match number.

    // Index 0: Match number.
    // Index 1-3: Blue teams 1-3.
    // Index 4-6: Red teams 1-3.

    // When-statement to handle isFiltered's condition (either true or false).
    // The for-loop is inside each lambda instead of having each lambda inside of a for-loop because
    // the for-loop would have to iterate through both when-statement cases every iteration while
    // when the for-loop is inside each when-statement case, it only has to iterate when deemed necessary.

    // This results in duplicating for-loop code. Duplication can be avoided in the future by creating
    // a function to handle matchScheduleList parsing into a type Match object.
    when (isFiltered) {
        true -> {
            for (match in matchScheduleList) {
                // Iterate through the next for-loop element when the provided matchNumber is not
                // the match number of the current loop cycle.
                if (match.trim().split(" ")[0] != matchNumber.toString()) {
                    continue
                }
                val currentMatch = Match(matchNumber.toString())
                for (index in 1..3) currentMatch.blueTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
                for (index in 4..6) currentMatch.redTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
                currentMatch.matchNumber = match.trim().split(" ")[0]
                matchScheduleMap[match.trim().split(" ")[0]] = currentMatch
                // Once the previous code is run, it means that the current for-loop cycle is for the
                // correct match number, meaning that it is okay to break out of the loop to return the
                // map containing only one key and one value.
                break
            }
        }
        false -> {
            if (MainViewerActivity.matchCache.size == matchScheduleList.size) {
                return MainViewerActivity.matchCache
            }
            for (match in matchScheduleList) {
                val currentMatch = Match(matchNumber.toString())
                for (index in 1..3) currentMatch.blueTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
                for (index in 4..6) currentMatch.redTeams.add(removeTeamPrefix(match.trim().split(" ")[index]))
                currentMatch.matchNumber = match.trim().split(" ")[0]
                matchScheduleMap[match.trim().split(" ")[0]] = currentMatch
                if (match.trim().split(" ")[0] !in MainViewerActivity.matchCache) {
                    MainViewerActivity.matchCache[match.trim().split(" ")[0]] = currentMatch
                }
            }
        }
    }
    return matchScheduleMap
}
