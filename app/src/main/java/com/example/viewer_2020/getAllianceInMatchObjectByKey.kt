/*
* getAllianceMatchObjectByKey.kt
* viewer
*
* Created on 2/18/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.example.viewer_2020.constants.Constants

// Returns a string value of any alliance object in the database as long as you provide it with
// the match number and the alliance of the color (and the requested field).
// 'path' is for whichever branch of alliances you want (ex. predicted_aim).

// It WILL iterate through every object in the given path until it finds the correct one which is a
// bit heavy, yet there's no obvious better way to do it given the structure of our database.

// If the value cannot be found, then it returns whatever character is set in Constants -> NULL_CHARACTER.
fun getAllianceInMatchObjectByKey(path: String, allianceColor: String, matchNumber: String, field: String): String {
    for (`object` in getDirectField(MainViewerActivity.databaseReference!!.processed, path)
            as Array<*>) {
        if (getDirectField(`object`!!, "match_number").toString() == matchNumber &&
                getDirectField(`object`, "alliance_color").toString() == allianceColor) {
            return getDirectField(`object`, field).toString()
        }
    }
    return Constants.NULL_CHARACTER
}