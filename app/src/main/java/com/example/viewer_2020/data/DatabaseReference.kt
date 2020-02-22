/*
* DatabaseReference.kt
* viewer
*
* Created on 2/2/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.data

import org.bson.types.ObjectId

//Database reference class to make a database object from MongoDB.
class DatabaseReference {
    data class CompetitionObject (
        var _id: ObjectId,
        var year: Int,
        var week_num: Int,
        var tba_event_key: String,
        var raw: Raw,
        var tba_cache: Array<String>,
        var processed: Processed
    )

    data class Raw (
        var qr: Array<String>,
        var pit: Array<String>
    )

    data class Processed (
        var replay_outdated_qr: Array<String>,
        var unconsolidated_obj_tim: Array<UnconsolidatedObjectiveTeamInMatch>,
        var calc_obj_tim: Array<String>,
        var subj_aim: Array<String>,
        var calc_obj_team: Array<CalculatedObjectiveTeam>,
        var calc_subj_team: Array<CalculatedSubjectiveTeam>,
        var calc_match: Array<String>,
        var calc_predicted_aim: Array<CalculatedPredictedAllianceInMatch>,
        var calc_predicted_team: Array<CalculatedPredictedTeam>,
        var calc_tba_team: Array<CalculatedTBATeam>,
        var calc_pick_ability_team: Array<CalculatedPickAbilityTeam>
    )

    data class CalculatedPredictedAllianceInMatch (
        var match_number: Int,
        var alliance_color: String,
        var predicted_score: Float,
        var predicted_rp1: Float,
        var predicted_rp2: Float
    )

    data class CalculatedTBATeam (
        var auto_high_balls_percent_inner: Float,
        var climb_all_successes: Int,
        var climb_solo_level_successes: Int,
        var park_successes: Int,
        var climb_all_successes_avg_time: Float,
        var auto_line_successes: Float,
        var team_number: Int
    )

    data class CalculatedPickAbilityTeam (
        var auto_high_balls_percent_inner: Boolean,
        var tele_high_balls_percent_inner: Boolean,
        var climb_all_successes: Int,
        var climb: Boolean,
        var climb_solo_level_successes: Int,
        var solo_level_climb: Boolean,
        var park: Int,
        var auto_line_successes: Boolean,
        var team_number: Int
    )

    data class CalculatedPredictedTeam (
        var predicted_rps: Double,
        var predicted_rank: Double,
        var current_rps: Int,
        var current_rank: Int,
        var current_avg_rps: Double,
        var team_number: Int
    )

    data class CalculatedSubjectiveTeam (
        var driver_ability: Float,
        var team_number: Int
    )

    data class CalculatedObjectiveTeam (
        var auto_avg_balls_low: Float,
        var auto_avg_balls_high: Float,
        var tele_avg_balls_low: Float,
        var tele_avg_balls_high: Float,
        var tele_avg_time_incap: Float,
        var climb_all_attempts: Int,
        var team_number: Int,
        var tele_cp_rotation_successes: Int,
        var tele_cp_position_successes: Int
    )

    data class UnconsolidatedObjectiveTeamInMatch (
        var schema_version: Int,
        var serial_number: String,
        var scout_name: String,
        var match_number: Int,
        var timestamp: Int,
        var team_number: Int,
        var timeline: Array<Timeline>
    )

    data class Timeline (
        var time: Int,
        var action_type: String
    )
}
