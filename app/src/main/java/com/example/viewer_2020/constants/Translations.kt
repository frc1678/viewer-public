package com.example.viewer_2020.constants

class Translations {
    companion object {
        val ACTUAL_TO_HUMAN_READABLE: Map<String, String> = mapOf(
            "auto_avg_balls_low" to "Auto Avg Low",
            "auto_avg_balls_high" to "Auto Avg High",
            "tele_avg_balls_low" to "Tele Avg Low",
            "tele_avg_balls_high" to "Tele Avg High",
            "climb_all_attempts" to "Climb Attempts",
            "predicted_rank" to "Predicted Rank",
            "predicted_rps" to "Predicted RPs",
            "current_seed" to "Current Seed",
            "current_rps" to "Current RPs",
            "current_avg_rps" to "Current Avg RPs",
            "auto_balls_low" to "Auto Balls Low",
            "auto_balls_high" to "Auto Balls High",
            "auto_avg_balls_total" to "Auto Avg Balls Total",
            "auto_high_balls_percent_inner" to "Auto High % Inner",
            "auto_line_successes" to "Auto Line Success",
            "tele_balls_low" to "Tele Balls Low",
            "tele_balls_high" to "Tele Balls High",
            "control_panel_rotation" to "Control Panel Rotation",
            "control_panel_position" to "Control Panel Position",
            "timeline_cycle_time" to "Timeline Cycle Time",
            "tele_cp_rotation_successes" to "Tele CP Rotation Successes",
            "tele_cp_position_successes" to "Tele CP Position Successes",
            "tele_high_balls_percent_inner" to "Tele High % Inner",
            "tele_avg_balls_total" to "Tele Avg Balls Total",
            "timeline_cycle_time" to "Timeline Cycle Time",
            "climb_all_successes" to "Climb All Successes",
            "climb_solo_level_successes" to "Climb Solo Level Successes",
            "park_successes" to "Park Successes",
            "auto_line_successes" to "Auto Line Successes",
            "climb_all_success_avg_time" to "Climb All Success Avg",
            "drivetrain_motor_type" to "Drivetrain Motor Type",
            "drivetrain_motors" to "Drivetrain Motors",
            "drivetrain" to "Drivetrain",
            "can_cross_trench" to "Can cross trench?",
            "has_ground_intake" to "Has ground intake?",
            "climber_strap_installation_notes" to "Climber Strap Installation Notes",
            "climber_strap_installation_time" to "Climber Strap Installation Time"
        )

        val DRIVETRAIN: Map<String, String> = mapOf(
            "0" to "tank",
            "1" to "mecanum",
            "2" to "swerve",
            "3" to "other"
        )

        val DRIVETRAIN_MOTOR_TYPE: Map<String, String> = mapOf(
            "0" to "minicim",
            "1" to "cim",
            "2" to "neo",
            "3" to "falcon"
        )

        val CLIMBER_STRAP_INSTALLATION_TIME: Map<String, String> = mapOf(
            "0" to "low",
            "1" to "average",
            "2" to "high",
            "3" to "impossible"
        )
    }
}