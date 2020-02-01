/*
* removeTeamPrefix.kt
* viewer
*
* Created on 1/30/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

//Removes the R-/B- prefix from the team number from the csv file.
//Ex. R-1678 -> 1678
fun removeTeamPrefix(teamNumberWithPrefix: String): String {
    return (teamNumberWithPrefix.substring(teamNumberWithPrefix.indexOf("-") + 1, teamNumberWithPrefix.length))
}