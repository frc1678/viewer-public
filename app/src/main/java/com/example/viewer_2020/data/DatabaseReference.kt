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
        var obj_pit: Array<ObjectivePit>,
        var subj_pit: Array<SubjectivePit>
    )

    data class ObjectivePit (
        var team_number: Int,
        var can_cross_trench: Boolean,
        var drivetrain: Int, //todo IF APP CRASHES,
        //todo THIS VALUE IS ACTUALLY AN ENUM BUT I HAVE NO IDEA WHY THEY THOUGHT IT
        //todo WAS A GOOD IDEA TO HAVE IT AS AN ENUM WHEN WE OBVIOUSLY CANNOT HANDLE ENUMS
        var drivetrain_motors: Int,
        var drivetrain_motor_type: Int, //todo IF APP CRASHES,
        //todo THIS VALUE IS ACTUALLY AN ENUM BUT I HAVE NO IDEA WHY THEY THOUGHT IT
        //todo WAS A GOOD IDEA TO HAVE IT AS AN ENUM WHEN WE OBVIOUSLY CANNOT HANDLE ENUMS
        var has_ground_intake: Boolean
    )

    data class SubjectivePit (
        var team_number: Int,
        var climber_strap_installation_notes: String,
        var climber_strap_installation_difficulty: Int //todo IF APP CRASHES,
        //todo THIS VALUE IS ACTUALLY AN ENUM BUT I HAVE NO IDEA WHY THEY THOUGHT IT
        //todo WAS A GOOD IDEA TO HAVE IT AS AN ENUM WHEN WE OBVIOUSLY CANNOT HANDLE ENUMS
    )

    data class Processed (
        var calc_obj_tim: Array<CalculatedObjectiveTeamInMatch>,
        var calc_obj_team: Array<CalculatedObjectiveTeam>,
        var calc_subj_team: Array<CalculatedSubjectiveTeam>,
        var calc_predicted_aim: Array<CalculatedPredictedAllianceInMatch>,
        var calc_predicted_team: Array<CalculatedPredictedTeam>,
        var calc_tba_team: Array<CalculatedTBATeam>,
        var calc_pick_ability_team: Array<CalculatedPickAbilityTeam>
    )

    data class CalculatedPredictedAllianceInMatch (
        var match_number: Int,
        var alliance_color_is_red: Boolean,
        var predicted_score: Float,
        var predicted_rp1: Float,
        var predicted_rp2: Float
    )

    data class CalculatedTBATeam (
        var team_number: Int,
        var team_name: String,
        var climb_all_success_avg_time: Float,
        var climb_all_successes: Int,
        var climb_solo_level_successes: Int,
        var park_successes: Int,
        var auto_line_successes: Int
    )

    data class CalculatedPickAbilityTeam (
        var team_number: Int,
        var first_pick_ability: Float,
        var second_pick_ability: Float
    )

    data class CalculatedPredictedTeam (
        var team_number: Int,
        var predicted_rps: Double,
        var predicted_rank: Int,
        var current_rps: Int,
        var current_rank: Int,
        var current_avg_rps: Float
    )

    data class CalculatedSubjectiveTeam (
        var team_number: Int,
        var driver_agility: Float,
        var driver_speed: Float,
        var driver_ability: Float
    )

    data class CalculatedObjectiveTeam (
        var team_number: Int,
        var auto_avg_balls_low: Float,
        var auto_avg_balls_high: Float,
        var tele_avg_balls_low: Float,
        var tele_avg_balls_high: Float,
        var tele_cp_rotation_successes: Int,
        var tele_cp_position_successes: Int,
        var climb_all_attempts: Int
    )

    data class CalculatedObjectiveTeamInMatch (
        var team_number: Int,
        var match_number: Int,
        var auto_balls_low: Int,
        var auto_balls_high: Int,
        var tele_balls_low: Int,
        var tele_balls_high: Int,
        var control_panel_rotation: Boolean,
        var control_panel_position: Boolean,
        var timeline_cycle_time: Int
    )
}
