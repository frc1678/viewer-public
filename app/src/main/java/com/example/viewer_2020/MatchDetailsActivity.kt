/*
* MatchDetailsActivity.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.match_details.*

// Displays the match details of a match from the match schedule.
// Provides a summary for each team in the match and the predicted score for both alliances.
class MatchDetailsActivity : ViewerActivity() {

    private var matchNumber: Int? = null

    // Returns each of the six team's summary lists that are displayed in each team's section of the match details.
    private fun getListViewCollection(): List<ListView> {
        return listOf<ListView>(lv_team_one, lv_team_two, lv_team_three,
            lv_team_four, lv_team_five, lv_team_six)
    }

    // Returns each of the six team's team number display.
    private fun getTeamNumberCollection(): List<TextView> {
        return listOf<TextView>(tv_team_one, tv_team_two, tv_team_three,
            tv_team_four, tv_team_five, tv_team_six)
    }

    // Prepare the MatchDetails activity by populating each text view and other XML element
    // with its match specific information.
    private fun populateMatchDetailsEssentials() {
        // If an intent exists from the previous activity (MainViewerActivity), then set the
        // match number display on MatchDetails to the match number provided with the intent.

        // If the match number from the MainViewerActivity's match schedule list view cell position
        // is null, the default display will show '0' for the match number on MatchDetails.
        intent?.let {
            matchNumber = it.getIntExtra(Constants.MATCH_NUMBER, 0)
        }
        tv_match_number_display.text = matchNumber.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_details)
        populateMatchDetailsEssentials()
        supportActionBar?.hide()

        // For every team in the match details, we set the adapter for their list view according to
        // their team number and the current type Match object. We also include a list of the
        // data points we expect to be displayed on the MatchDetails list view.
        for (listView in getListViewCollection()) {
            listView.adapter = MatchDetailsAdapter(
                context = this,
                datapointsDisplayed = Constants.FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS
            )
        }

        for (teamNumber in getTeamNumberCollection()) {
            // If the index of the team number in the team number list is below 3, it means that the
            // team number is a team from the blue alliance.

            // The teamNumber.text prompt receives a string value, so we pull the filtered match schedule
            // that only contains the single match that is requested using the 'matchNumber' prompt, and
            // then we get the value of the matchNumber key of the single-match filtered match schedule map.

            // After getting the correct Match object value from the filtered match schedule map, we
            // set the teamNumber text to the correct team index of the blueTeams/redTeams list of the Match object.
            // We get the index by checking which index of the teamNumber collection list the current team
            // number iteration is.
            if (getTeamNumberCollection().indexOf(teamNumber) < 3) {
                teamNumber.text = convertMatchScheduleListToMap(csvFileRead("match_schedule.csv", false),
                    isFiltered = true,
                    matchNumber = matchNumber)!![matchNumber.toString()]!!.blueTeams[getTeamNumberCollection().indexOf(teamNumber)]
            } else {
                teamNumber.text = convertMatchScheduleListToMap(csvFileRead("match_schedule.csv", false),
                    isFiltered = true,
                    matchNumber = matchNumber)!![matchNumber.toString()]!!.redTeams[getTeamNumberCollection().indexOf(teamNumber) - 3]
            }
        }
    }
}