/*
* Constants.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.constants

//Class that contains a collection of Constant values, or final values that never change
class Constants {
    companion object {
        //Game specific data.
        const val TBA_EVENT_KEY = "2020mosl"
        const val DATABASE_NAME = "scouting_system_cloud"
        const val COLLECTION_NAME = "competitions"
        const val MONGO_ATLAS = "mongodb-atlas"
        const val MY_TEAM_NUMBER = "1678"

        val FIELDS_TO_BE_DISPLAYED: List<String> = listOf(
            "processed",
            "raw"
        )

        val FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS: Map<String, ArrayList<String>> = mapOf(
            "Auto" to arrayListOf(
                "auto_avg_balls_low",
                "auto_avg_balls_high",
                "auto_line_successes"
            ),
            "Tele" to arrayListOf(
                "driver_ability",
                "tele_avg_balls_low",
                "tele_avg_balls_high",
                "tele_cp_rotation_successes",
                "tele_cp_position_successes"
            ),
            "Endgame" to arrayListOf(
                "climb_all_successes",
                "climb_all_attempts",
                "climb_all_success_avg_time",
                "climber_strap_installation_difficulty",
                "climb_level_successes"
            )
        )

        val FIELDS_TO_BE_DISPLAYED_TEAM_DETAILS: Map<String, ArrayList<String>> = mapOf(
            "Auto" to arrayListOf(
                "auto_avg_balls_low",
                "auto_avg_balls_high",
                "auto_line_successes"
            ),
            "Tele" to arrayListOf(
                "team_name",
                "driver_ability",
                "tele_avg_balls_low",
                "tele_avg_balls_high",
                "tele_cp_rotation_successes",
                "tele_cp_position_successes",
                "can_cross_trench",
                "drivetrain",
                "drivetrain_motors",
                "drivetrain_motor_type",
                "has_ground_intake"
            ),
            "Endgame" to arrayListOf(
                "climb_all_successes",
                "climb_all_attempts",
                "climb_all_success_avg_time",
                "climber_strap_installation_difficulty",
                "climb_level_successes",
                "park_successes",
                "climber_strap_installation_notes"
            )
        )

        val FIELDS_TO_BE_DISPLAYED_RANKING: List<String> = listOf(
            "current_seed",
            "current_avg_rps",
            "current_rps",
            "predicted_rps",
            "predicted_rank"
        )

        val FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS_HEADER: List<String> = listOf(
            "predicted_rp1",
            "predicted_score",
            "predicted_rp2"
        )

        //String literal translations.
        const val TEAM_NUMBER = "teamNumber"
        const val MATCH_NUMBER = "matchNumber"
        const val BLUE = "blue"
        const val RED = "red"

        //Util.
        const val NULL_PREDICTED_SCORE_CHARACTER = "-"
        const val NULL_CHARACTER = "?"
        const val EMPTY_CHARACTER = ""
        const val RANKING_POINT_CHARACTER = "•"
        const val PREDICTED_RANKING_POINT_QUALIFICATION = 0.65
    }

    enum class PROCESSED_OBJECT(val value: String) {
        CALCULATED_OBJECTIVE_TEAM_IN_MATCH("calc_obj_tim"),
        CALCULATED_OBJECTIVE_TEAM("calc_obj_team"),
        CALCULATED_SUBJECTIVE_TEAM("calc_subj_team"),
        CALCULATED_PREDICTED_ALLIANCE_IN_MATCH("calc_predicted_aim"),
        CALCULATED_PREDICTED_TEAM("calc_predicted_team"),
        CALCULATED_TBA_TEAM("calc_tba_team")
    }
}