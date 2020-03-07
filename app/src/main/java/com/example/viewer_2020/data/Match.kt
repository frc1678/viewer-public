/*
* Match.kt
* viewer
*
* Created on 1/30/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.data

// Data storage class for each individual match object.
data class Match(var matchNumber: String) {
    var redTeams: ArrayList<String> = ArrayList()
    var blueTeams: ArrayList<String> = ArrayList()
}