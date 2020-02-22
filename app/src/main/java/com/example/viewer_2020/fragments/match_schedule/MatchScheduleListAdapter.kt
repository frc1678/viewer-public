/*
* MatchScheduleListAdapter.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.fragments.match_schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.viewer_2020.data.Match
import com.example.viewer_2020.R
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.getAllianceInMatchObjectByKey

//Custom list adapter class with Match object handling to display the custom cell for the match schedule.
class MatchScheduleListAdapter(private val context: Context, private val matchContents : HashMap<String, Match>) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    //Return the size of the match schedule.
    override fun getCount(): Int {
        return matchContents.size
    }

    //Return the Match object given the match number.
    override fun getItem(position: Int): Any {
        return matchContents[(position + 1).toString()] as Any
    }

    //Return the position of the cell.
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //Populate the elements of the custom cell.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View?
        val matchNumber: String = (position + 1).toString()
        if (convertView == null) {
            rowView = inflater.inflate(R.layout.match_schedule_cell, parent, false)
            viewHolder =
                ViewHolder(
                    rowView
                )
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        for (tv in listOf(viewHolder.tvRedTeamOne, viewHolder.tvRedTeamTwo, viewHolder.tvRedTeamThree)) {
            tv.text = matchContents[matchNumber]!!.redTeams[0 +
                    listOf(viewHolder.tvRedTeamOne, viewHolder.tvRedTeamTwo, viewHolder.tvRedTeamThree).indexOf(tv)]
        }
        for (tv in listOf(viewHolder.tvBlueTeamOne, viewHolder.tvBlueTeamTwo, viewHolder.tvBlueTeamThree)) {
            tv.text = matchContents[matchNumber]!!.blueTeams[0 +
                    listOf(viewHolder.tvBlueTeamOne, viewHolder.tvBlueTeamTwo, viewHolder.tvBlueTeamThree).indexOf(tv)]
        }
        viewHolder.tvMatchNumber.text = matchNumber
        viewHolder.tvBluePredictedScore.text =
            if (getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.BLUE, matchNumber, "predicted_score"
                ) != Constants.NULL_CHARACTER)
                getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.BLUE, matchNumber, "predicted_score"
                )
            else Constants.NULL_PREDICTED_SCORE_CHARACTER

        viewHolder.tvRedPredictedScore.text =
            if (getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.RED, matchNumber, "predicted_score"
                ) != Constants.NULL_CHARACTER)
                getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.RED, matchNumber, "predicted_score"
                )
            else Constants.NULL_PREDICTED_SCORE_CHARACTER
        for (tv in listOf(viewHolder.tvBluePredictedScore, viewHolder.tvRedPredictedScore)) {
            if (tv.text == Constants.NULL_PREDICTED_SCORE_CHARACTER) {
                tv.setTextColor(ContextCompat.getColor(context,
                    R.color.ElectricGreen
                ))
            } else {
                tv.setTextColor(ContextCompat.getColor(context,
                    R.color.Black
                ))
            }
        }
        return rowView!!
    }

    //View holder class to handle the elements used in the custom cells.
    private class ViewHolder(view: View?) {
        val tvMatchNumber = view?.findViewById(R.id.tv_match_number) as TextView
        val tvBluePredictedScore = view?.findViewById(R.id.tv_blue_predicted_score) as TextView
        val tvRedPredictedScore = view?.findViewById(R.id.tv_red_predicted_score) as TextView
        val tvBlueTeamOne = view?.findViewById(R.id.tv_blue_team_one) as TextView
        val tvBlueTeamTwo = view?.findViewById(R.id.tv_blue_team_two) as TextView
        val tvBlueTeamThree = view?.findViewById(R.id.tv_blue_team_three) as TextView
        val tvRedTeamOne = view?.findViewById(R.id.tv_red_team_one) as TextView
        val tvRedTeamTwo = view?.findViewById(R.id.tv_red_team_two) as TextView
        val tvRedTeamThree = view?.findViewById(R.id.tv_red_team_three) as TextView
    }
}