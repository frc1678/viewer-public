package com.example.viewer_2020

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.match_details.*

class MatchDetailsActivity : AppCompatActivity() {

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