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
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.match_details.*

// Displays the match details of a match from the match schedule.
// Provides a summary for each team in the match and the predicted score for both alliances.
class MatchDetailsActivity : AppCompatActivity() {

    // Returns each of the six team's summary lists that are displayed in each team's section of the match details.
    private fun getListViewCollection(): List<ListView> {
        return listOf<ListView>(lv_team_one, lv_team_two, lv_team_three,
            lv_team_four, lv_team_five, lv_team_six)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_details)

        //todo GET SPECIFIC LIST VIEW BY FINDING INDEX USING getListViewCollection()
        for (listView in getListViewCollection()) {
            listView.adapter = MatchDetailsAdapter(this, MatchDetails)
        }
}}