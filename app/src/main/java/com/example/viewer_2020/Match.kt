/*
* Match.kt
* viewer
*
* Created on 1/30/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

//Data storage class for each individual match object.
class Match {
    var matchNumber: String? = null
    var redTeams = ArrayList<String>()
    var blueTeams = ArrayList<String>()
    var redActualScore: Int? = null
    var blueActualScore: Int? = null
}