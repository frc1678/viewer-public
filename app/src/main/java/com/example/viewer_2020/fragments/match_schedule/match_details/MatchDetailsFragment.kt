/*
* MatchDetailsFragment.kt
* viewer
*
* Created on 2/10/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020.fragments.match_schedule.match_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewer_2020.*
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.fragments.team_details.TeamDetailsFragment
import kotlinx.android.synthetic.main.match_details.view.*

// The fragment class for the Match Details display that occurs when you click on a
// match in the match schedule page.
class MatchDetailsFragment : Fragment() {

    private var matchNumber: Int? = null
    private var currentMatchDetailsSectionMenuItem: MenuItem? = null

    private val teamDetailsFragment = TeamDetailsFragment()
    private val teamDetailsFragmentArguments = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.match_details, container, false)
        if (currentMatchDetailsSectionMenuItem == null) currentMatchDetailsSectionMenuItem =
            root.nav_match_details_view.menu.getItem(0); updateTeamListViews(root)

        populateMatchDetailsEssentials(root)

        // This creates the on menu select listener for the MatchDetails fragment navigation bar.
        // The purpose of this navigation bar is to switch between the type of data that each
        // team's list view in match details displays. The list view adapter contents can be altered
        // in Constants.kt -> FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS. FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS
        // is a map of a string key to arraylist<string> value, with each string key being the menu
        // items and the contents of each arraylist<string> being the specific data points displayed
        // in each of the menu item's adapter settings sections.
        root.nav_match_details_view.setOnNavigationItemSelectedListener {
            if (currentMatchDetailsSectionMenuItem != it) {
                it.isChecked = true
                currentMatchDetailsSectionMenuItem = it
                updateTeamListViews(root)
            }
            return@setOnNavigationItemSelectedListener true
        }

        for (teamNumber in getTeamNumberCollection(root)) {
            // If the index of the team number in the team number list is below 3, it means that the
            // team number is a team from the blue alliance.

            // The teamNumber.text prompt receives a string value, so we pull the filtered match schedule
            // that only contains the single match that is requested using the 'matchNumber' prompt, and
            // then we get the value of the matchNumber key of the single-match filtered match schedule map.

            // After getting the correct Match object value from the filtered match schedule map, we
            // set the teamNumber text to the correct team index of the blueTeams/redTeams list of the Match object.
            // We get the index by checking which index of the teamNumber collection list the current team
            // number iteration is.
            if (getTeamNumberCollection(root).indexOf(teamNumber) < 3) {
                teamNumber.text = convertMatchScheduleListToMap(
                    csvFileRead("match_schedule.csv", false),
                    isFiltered = true,
                    matchNumber = matchNumber)!![matchNumber.toString()]!!.blueTeams[getTeamNumberCollection(root).indexOf(teamNumber)]
            } else {
                teamNumber.text = convertMatchScheduleListToMap(
                    csvFileRead("match_schedule.csv", false),
                    isFiltered = true,
                    matchNumber = matchNumber)!![matchNumber.toString()]!!.redTeams[getTeamNumberCollection(root).indexOf(teamNumber) - 3]
            }

            // We run this method because the code above sets each team number text view to the
            // specified team number, and both the updateTeamListViews method and
            // the initTeamNumberClickListener methods pull the text view's text
            // value to access each team number.
            updateTeamListViews(root)
            initTeamNumberClickListeners(root)
        }
        return root
    }

    // Returns each of the six team's summary lists that are displayed in each team's section of the match details.
    private fun getListViewCollection(root: View): List<ListView> {
        return listOf<ListView>(root.lv_team_one, root.lv_team_two, root.lv_team_three,
            root.lv_team_four, root.lv_team_five, root.lv_team_six)
    }

    // Returns each of the six team's team number display.
    private fun getTeamNumberCollection(root: View): List<TextView> {
        return listOf<TextView>(root.tv_team_one, root.tv_team_two, root.tv_team_three,
            root.tv_team_four, root.tv_team_five, root.tv_team_six)
    }

    // Returns each of the three match details header text views that lay beside the team number.
    private fun getHeaderCollection(root: View): List<TextView> {
        return listOf<TextView>(root.tv_header_one, root.tv_header_two, root.tv_header_three,
            root.tv_header_four, root.tv_header_five, root.tv_header_six)
    }

    // On every team number's specified text view, when the user clicks on the text view it will
    // then go to a new TeamDetails page for the given team number.
    private fun initTeamNumberClickListeners(root: View) {
        val matchDetailsFragmentTransaction = this.fragmentManager!!.beginTransaction()
        for (tv in getTeamNumberCollection(root)) {
            tv.setOnClickListener {
                teamDetailsFragmentArguments.putString(Constants.TEAM_NUMBER, tv.text.toString())
                teamDetailsFragment.arguments = teamDetailsFragmentArguments
                matchDetailsFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                matchDetailsFragmentTransaction.replace((view!!.parent as ViewGroup).id, teamDetailsFragment).commit()
            }
        }
    }

    // Updates the adapter for the list view of each team in the match details display.
    private fun updateTeamListViews(root: View) {
        // For every team in the match details, we set the adapter for their list view according to
        // their team number and the current type Match object. We also include a list of the
        // data points we expect to be displayed on the MatchDetails list view.
        for (listView in getListViewCollection(root)) {
            listView.adapter =
                MatchDetailsAdapter(
                    context = activity!!,
                    datapointsDisplayed = Constants.FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS,
                    currentSection = currentMatchDetailsSectionMenuItem.toString(),
                    teamNumber = getTeamNumberCollection(root)[getListViewCollection(root).indexOf(
                        listView
                    )].text.toString()
                )
        }
    }

    // Prepare the MatchDetails activity by populating each text view and other XML element
    // with its match specific information.
    private fun populateMatchDetailsEssentials(root: View) {
        // If a fragment intent (bundle arguments) exists from the previous activity (MainViewerActivity),
        // then set the match number display on MatchDetails to the match number provided with the intent.

        // If the match number from the MainViewerActivity's match schedule list view cell position
        // is null, the default display will show '0' for the match number on MatchDetails.
        arguments?.let {
            matchNumber = it.getInt(Constants.MATCH_NUMBER, 0)
        }
        root.tv_match_number_display.
            text = matchNumber.toString()

        for (tv in getHeaderCollection(root)) {
            if (getHeaderCollection(root).indexOf(tv) < 3) {
                tv.text = getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.BLUE, matchNumber.toString(),
                    Constants.FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS_HEADER[getHeaderCollection(root).indexOf(tv)])
            } else {
                tv.text = getAllianceInMatchObjectByKey(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_ALLIANCE_IN_MATCH.value,
                    Constants.RED, matchNumber.toString(),
                    Constants.FIELDS_TO_BE_DISPLAYED_MATCH_DETAILS_HEADER[getHeaderCollection(root).indexOf(tv) - 3])
            }
        }
    }
}