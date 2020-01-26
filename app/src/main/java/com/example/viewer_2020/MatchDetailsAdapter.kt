package com.example.viewer_2020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.match_details_cell.view.*


class MatchDetailsAdapter(private val context: Context, private val matchContents : ArrayList<String>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return matchContents.size
    }

    override fun getItem(position: Int): Any {
        return matchContents[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.match_details_cell, parent, false)
        rowView.tv_rank.text = (position + 1).toString() //todo CHANGE TO RANK COMPARED TO ALL TEAMS
        rowView.tv_datapoint_name.text = matchContents[position]
        rowView.tv_value.text = (position + 1).toString() //todo CHANGE TO VALUE OF DATAPOINT FOR THE GIVEN TEAM


        return rowView
    }
}