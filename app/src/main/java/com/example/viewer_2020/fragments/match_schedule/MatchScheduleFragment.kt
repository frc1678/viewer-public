/*
* MatchScheduleFragment.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.data.Match
import com.example.viewer_2020.fragments.match_schedule.MatchScheduleListAdapter
import com.example.viewer_2020.fragments.match_schedule.match_details.MatchDetailsFragment
import kotlinx.android.synthetic.main.fragment_match_schedule.view.*
import kotlinx.android.synthetic.main.team_details.view.*

//The fragment of the match schedule 'view' that is one of the options of the navigation bar.
class MatchScheduleFragment : Fragment() {

    private val matchScheduleViewModel by lazy {
        ViewModelProviders.of(this).get(MatchScheduleViewModel::class.java)
    }

    private var currentMatchScheduleSectionMenuItem: MenuItem? = null

    private val matchDetailsFragment = MatchDetailsFragment()
    private val matchDetailsFragmentArguments = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_match_schedule, container, false)

        if (currentMatchScheduleSectionMenuItem == null) currentMatchScheduleSectionMenuItem =
            root.nav_match_schedule_view.menu.getItem(0)
        updateMatchScheduleListView(root)

        // This creates the on menu select listener for the MatchSchedule fragment navigation bar.
        // The purpose of this navigation bar is to switch between the type of data that the
        // list view in match schedule displays. The list view adapter contents come from the
        // match_schedule.csv file in the phone storage.
        root.nav_match_schedule_view.setOnNavigationItemSelectedListener {
            if (currentMatchScheduleSectionMenuItem != it) {
                it.isChecked = true
                currentMatchScheduleSectionMenuItem = it
                updateMatchScheduleListView(root)
            }
            return@setOnNavigationItemSelectedListener true
        }

        val matchDetailsFragmentTransaction = this.fragmentManager!!.beginTransaction()
        // When an item click occurs, go to the MatchDetails fragment of the match item clicked.
        root.lv_match_schedule.setOnItemClickListener { _, _, position, _ ->
            when (currentMatchScheduleSectionMenuItem.toString()) {
                "Our Schedule" -> {
                    matchDetailsFragmentArguments.putInt(Constants.MATCH_NUMBER,
                        getTeamSpecificMatchNumbers(Constants.MY_TEAM_NUMBER)[position].toInt())
                }
                "Match Schedule" -> {
                    matchDetailsFragmentArguments.putInt(Constants.MATCH_NUMBER, position + 1)
                }
            }
            matchDetailsFragment.arguments = matchDetailsFragmentArguments
            matchDetailsFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            matchDetailsFragmentTransaction.replace(
                (view!!.parent as ViewGroup).id,
                matchDetailsFragment
            ).commit()
        }
        return root
    }

    private fun updateMatchScheduleListView(root: View) {
        root.lv_match_schedule.adapter =
            MatchScheduleListAdapter(
                activity!!,
                (convertMatchScheduleListToMap(
                    csvFileRead("match_schedule.csv", false),
                    isFiltered = false,
                    matchNumber = null
                )!!
                        ),
                currentMatchScheduleSectionMenuItem.toString()
            )
    }
}