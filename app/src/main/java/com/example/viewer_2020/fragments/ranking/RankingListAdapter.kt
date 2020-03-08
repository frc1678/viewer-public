/*
* RankingListAdapter.kt
* viewer
*
* Created on 2/8/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.fragments.ranking

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.viewer_2020.R
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.getTeamObjectByKey

// Custom list adapter class with aq object handling to display the custom cell for the match schedule.
class RankingListAdapter(
    private val activity: Activity,
    private val listContents: List<String>
): BaseAdapter() {

    private val inflater = LayoutInflater.from(activity)

    // Return the size of the match schedule.
    override fun getCount(): Int {
        return listContents.size
    }

    // Return the Match object given the match number.
    override fun getItem(position: Int): Any {
        return listContents[position]
    }

    // Return the position of the cell.
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Populate the elements of the custom cell.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View?

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.seeding_cell, parent, false)
            viewHolder =
                ViewHolder(
                    rowView
                )
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        viewHolder.tvTeamNumber.text = listContents[position]

        viewHolder.tvDatapointOne.text = getTeamObject("current_rank",
            position, Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value)
        viewHolder.tvDatapointTwo.text = getTeamObject("current_avg_rps",
            position, Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value)
        viewHolder.tvDatapointThree.text = getTeamObject("current_rps",
            position, Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value)
        viewHolder.tvDatapointFour.text = getTeamObject("predicted_rps",
            position, Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value)
        viewHolder.tvDatapointFive.text = getTeamObject("predicted_rank",
            position, Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value)

        return rowView!!
    }

    private fun getTeamObject(field: String, position: Int, path: String): String {
        return getTeamObjectByKey(
            path, listContents[position],
            field)
    }
}
// View holder class to handle the elements used in the custom cells.
private class ViewHolder(view: View?) {
    val tvTeamNumber = view?.findViewById(R.id.tv_team_number) as TextView
    val tvDatapointOne = view?.findViewById(R.id.tv_datapoint_one) as TextView
    val tvDatapointTwo = view?.findViewById(R.id.tv_datapoint_two) as TextView
    val tvDatapointThree = view?.findViewById(R.id.tv_datapoint_three) as TextView
    val tvDatapointFour = view?.findViewById(R.id.tv_datapoint_four) as TextView
    val tvDatapointFive = view?.findViewById(R.id.tv_datapoint_five) as TextView
}