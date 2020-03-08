/*
* RankingFragment.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.constants.Translations
import com.example.viewer_2020.fragments.ranking.RankingListAdapter
import com.example.viewer_2020.fragments.team_details.TeamDetailsFragment
import kotlinx.android.synthetic.main.fragment_ranking.view.*

// The fragment of the ranking lists 'view' that is one of the options of the navigation bar.
// Disclaimer: This fragment contains another menu bar which is displayed directly above the
// main menu bar. This navigation/menu bar does not switch between fragments on each menu's selection
// like the main menu bar does. This navigation bar only receives the position/ID of the menu selected
// and then updated the adapter of the list view that is right above it.
class RankingFragment : Fragment() {

    private val teamDetailsFragment = TeamDetailsFragment()
    private val teamDetailsFragmentArguments = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Usage of counter variable to increment at every iteration of the menu item forEach statement.
        // Used to find the index of the current selected menu in the menu item list because the
        // MenuItem object does not have an indexOf() return function.
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)
        // This is a listener for the navigation bar onClick.
        // When a menu item of the navigation bar is clicked, it sends the MenuItem to
        // the handler activity (MainViewerActivity) to set the adapter of this fragment's
        // resource layout to the correct adapter given the new menu item that should be displayed.

        root.tv_datapoint_two.text =
            Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_RANKING[1]]
        root.tv_datapoint_three.text =
            Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_RANKING[2]]
        root.tv_datapoint_four.text =
            Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_RANKING[3]]

        root.lv_ranking.adapter = RankingListAdapter(activity!!, convertToFilteredTeamsList(
            Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value,
            csvFileRead("team_list.csv", false)[0].trim().split(" ")
        ))

        root.lv_ranking.setOnItemClickListener { _, _, position, _ ->
            val rankingFragmentTransaction = this.fragmentManager!!.beginTransaction()
            teamDetailsFragmentArguments.putString(
                Constants.TEAM_NUMBER, convertToFilteredTeamsList(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value,
                    csvFileRead("team_list.csv", false)[0].trim().split(" ")
                )[position]
            )
            teamDetailsFragment.arguments = teamDetailsFragmentArguments
            rankingFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            rankingFragmentTransaction.replace(
                (view!!.parent as ViewGroup).id,
                teamDetailsFragment
            ).commit()
        }
        return root
    }
}