/*
* RankingListAdapter.kt
* viewer
*
* Created on 2/8/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// Custom list adapter class with aq object handling to display the custom cell for the match schedule.
class RankingListAdapter(
        private val context: Context,
        private val field: String,
        private val isSeeding: Boolean
): BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val listContents = ArrayList<String>()

    init {
        for (i in 1..30) {
            listContents.add(i.toString())
        }
    }

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
            rowView = inflater.inflate(R.layout.ranking_list_cell, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        //todo UPDATE ONCE DATABASE WORKS.
        when (field) {
            "Current Seed" -> {
                for (tv in listOf(viewHolder.tvOne, viewHolder.tvTwo, viewHolder.tvThree, viewHolder.tvFour)) {
                    tv.text = "CS"
                }
            }
            "Predicted Seed" -> {
                for (tv in listOf(viewHolder.tvOne, viewHolder.tvTwo, viewHolder.tvThree, viewHolder.tvFour)) {
                    tv.text = "PS"
                }
            }
            "First Pick" -> {
                for (tv in listOf(viewHolder.tvOne, viewHolder.tvTwo, viewHolder.tvThree, viewHolder.tvFour)) {
                    tv.text = "FP"
                }
            }
            "Second Pick" -> {
                for (tv in listOf(viewHolder.tvOne, viewHolder.tvTwo, viewHolder.tvThree, viewHolder.tvFour)) {
                    tv.text = "SP"
                }
            }
        }
        return rowView!!
    }

    // View holder class to handle the elements used in the custom cells.
    private class ViewHolder(view: View?) {
        val tvOne = view?.findViewById(R.id.one) as TextView
        val tvTwo = view?.findViewById(R.id.two) as TextView
        val tvThree = view?.findViewById(R.id.three) as TextView
        val tvFour = view?.findViewById(R.id.four) as TextView
    }
}