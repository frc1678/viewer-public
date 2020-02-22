/*
* TeamDetailsFragment.kt
* viewer
*
* Created on 2/19/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.fragments.team_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewer_2020.*
import com.example.viewer_2020.constants.Constants
import kotlinx.android.synthetic.main.team_details.view.*

// The fragment class for the Team Details display that occurs when you click on a
// team in the match details page.
class TeamDetailsFragment : Fragment() {

    private var currentTeamDetailsSectionMenuItem: MenuItem? = null
    private var teamNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.team_details, container, false)
        if (currentTeamDetailsSectionMenuItem == null) currentTeamDetailsSectionMenuItem =
            root.nav_team_details_view.menu.getItem(0)

        populateTeamDetailsEssentials(root)
        updateDatapointDisplayListView(root)

        // This creates the on menu select listener for the TeamDetails fragment navigation bar.
        // The purpose of this navigation bar is to switch between the type of data that the
        // list view in team details displays. The list view adapter contents can be altered
        // in Constants.kt -> FIELDS_TO_BE_DISPLAYED_TEAM_DETAILS. FIELDS_TO_BE_DISPLAYED_TEAM_DETAILS
        // is a map of a string key to arraylist<string> value, with each string key being the menu
        // items and the contents of each arraylist<string> being the specific data points displayed
        // in each of the menu item's adapter settings sections.
        root.nav_team_details_view.setOnNavigationItemSelectedListener {
            if (currentTeamDetailsSectionMenuItem != it) {
                it.isChecked = true
                currentTeamDetailsSectionMenuItem = it
                updateTeamDetailsSectionDisplay(root)
                updateDatapointDisplayListView(root)
            }
            return@setOnNavigationItemSelectedListener true
        }
        return root
    }

    // Prepare the TeamDetails page by populating each text view and other XML element
    // with its team specific information.
    private fun populateTeamDetailsEssentials(root: View) {
        // If a fragment intent (bundle arguments) exists from the previous activity (MainViewerActivity),
        // then set the team number display on TeamDetails to the team number provided with the intent.

        // If the team number from the MainViewerActivity's match schedule list view cell position
        // is null, the default display will show '0' for the team number on TeamDetails.
        arguments?.let {
            teamNumber = it.getString(Constants.TEAM_NUMBER, Constants.NULL_CHARACTER)
        }
        root.tv_team_number.text = teamNumber.toString()
        updateTeamDetailsSectionDisplay(root)
    }

    // Updates the adapter for the list view of each team in the match details display.
    private fun updateDatapointDisplayListView(root: View) {
        // We set the adapter for their list view according to
        // the team number and the current section. We also include a list of the
        // data points we expect to be displayed on the TeamDetails list view.
        root.lv_datapoint_display.adapter =
            TeamDetailsAdapter(
                context = activity!!,
                datapointsDisplayed = Constants.FIELDS_TO_BE_DISPLAYED_TEAM_DETAILS,
                currentSection = currentTeamDetailsSectionMenuItem.toString(),
                teamNumber = teamNumber!!
            )
    }

    // Set the text of the current section selected on the TeamDetailsFragment.
    // The current sections are accessible in Constants.kt -> FIELDS_TO_BE_DISPLAYED_TEAM_DETAILS (keys).
    private fun updateTeamDetailsSectionDisplay(root: View) {
        root.tv_section_display.text = currentTeamDetailsSectionMenuItem.toString()
    }
}