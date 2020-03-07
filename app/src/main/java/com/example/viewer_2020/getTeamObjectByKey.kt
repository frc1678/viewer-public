/*
* getTeamObjectByKey.kt
* viewer
*
* Created on 2/18/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.data.Team

// Returns a string value of any team object in the database as long as you provide it with
// the team number and the requested field.
// 'path' is for whichever branch of alliances you want (ex. calc_obj_team).

// It WILL iterate through every object in the given path until it finds the correct one which is a
// bit heavy, yet there's no obvious better way to do it given the structure of our database.

// If the value cannot be found, then it returns whatever character is set in Constants -> NULL_CHARACTER.
fun getTeamObjectByKey(path: String, teamNumber: String, field: String): String {
    if (MainViewerActivity.teamCache.containsKey(teamNumber)) {
        // Two separate if statements to lower computations per interaction.
        if (getDirectField(
                MainViewerActivity.teamCache[teamNumber]!!,
                field
            ).toString() != Constants.NULL_CHARACTER
        ) {
            return getDirectField(MainViewerActivity.teamCache[teamNumber]!!, field).toString()
        }
    } else {
        // If the team object doesn't exist in the cache, create the team object in the team cache.
        MainViewerActivity.teamCache[teamNumber] = Team(teamNumber.toInt())
    }
    // This for loop will occur when the team does NOT exist in the cache, AND when the team DOES
    // exist in the cache but has a null value for the given field.
    for (`object` in getDirectField(MainViewerActivity.databaseReference!!.processed, path)
            as Array<*>) {
        if (getDirectField(`object`!!, "team_number").toString() == teamNumber) {
            // Creating two constant variables. One for the current null cache field, and the other
            // for the value that is going to replace it.
            val mField =
                MainViewerActivity.teamCache[teamNumber]!!::class.java.getDeclaredField(field)
            val mValue = getDirectField(`object`, field)

            // Set the accessibility to true and replace the null cache value with the database value.
            mField.isAccessible = true
            if (mValue != Constants.NULL_CHARACTER) {
                mField.set(MainViewerActivity.teamCache[teamNumber]!!, mValue)
            } else {
                mField.set(MainViewerActivity.teamCache[teamNumber]!!, null)
            }
            return mValue.toString()
        }
    }
    return Constants.NULL_CHARACTER
}