/*
* TeamInMatch.kt
* viewer
*
* Created on 3/7/2019
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.data

// Data storage class for each individual team in match object.
data class TeamInMatch(
    var team_number: Int? = null,
    var match_number: Int? = null
) {
    var auto_balls_low: Int? = null
    var auto_balls_high: Int? = null
    var tele_balls_low: Int? = null
    var tele_balls_high: Int? = null
    var control_panel_rotation: Boolean? = null
    var control_panel_position: Boolean? = null
    var timeline_cycle_time: Int? = null
}
