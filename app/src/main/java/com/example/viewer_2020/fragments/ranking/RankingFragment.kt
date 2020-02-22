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
import com.example.viewer_2020.fragments.team_details.TeamDetailsFragment
import kotlinx.android.synthetic.main.fragment_ranking.view.*

// The fragment of the ranking lists 'view' that is one of the options of the navigation bar.
// Disclaimer: This fragment contains another menu bar which is displayed directly above the
// main menu bar. This navigation/menu bar does not switch between fragments on each menu's selection
// like the main menu bar does. This navigation bar only receives the position/ID of the menu selected
// and then updated the adapter of the list view that is right above it.
class RankingFragment : Fragment() {

    private var mListener: ChildRankingFragmentHandler? = null

    private val teamDetailsFragment = TeamDetailsFragment()
    private val teamDetailsFragmentArguments = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Usage of counter variable to increment at every iteration of the menu item forEach statement.
        // Used to find the index of the current selected menu in the menu item list because the
        // MenuItem object does not have an indexOf() return function.
        var counter = 0
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)
        val menu = root.nav_ranking_view.menu
        // Sets the default navigation bar menu item to the leftmost menu item.
        // Reset is false because this function runs as the first function every time
        // the fragment is accessed because it is the first function in the onCreateView method.
        // If it were not false, then the MainViewerActivity.currentRankingMenuItem would get
        // reset to the zeroth index of the menu item list, which would never allow the
        // MainViewerActivity.currentRankingMenuItem value to change.
        mListener!!.childRankingFragmentHandlerResponse(menu.getItem(0), root, false)
        // The MainViewerActivity's currentRankingMenuItem isn't initialized on its initial call,
        // meaning that it is a nullable type with a default value of 'null'.
        // This means that MainViewerActivity.currentRankingMenuItem will get set after this function
        // is called on this fragment's first nav_ranking_view.setOnNavigationItemSelectedListener input.
        if (MainViewerActivity.currentRankingMenuItem != null) {
            // '?:' is an elvis operator safe call. MainViewerActivity.currentRankingMenuItem is a
            // mutable variable, meaning that according to Kotlin, it is always able to change, meaning
            // that it's impossible to simply 'pull' it at any point because it is 'always changing'.
            // In order to bypass this handling error, we need to use a safe call operator that gives
            // an alternative value if the suggested value (MainViewerActivity.currentRankingMenuItem) errors.
            mListener!!.childRankingFragmentHandlerResponse(
                MainViewerActivity.currentRankingMenuItem ?: return null, root, true
            )
            // In order to get the index of the currentRankingMenuItem in the list of menu items,
            // we must iterate through each menu item and increment a third party local counter variable.
            // If the current selected ranking menu item is equal to the current menu item of the
            // menu list during iteration, then it sets the menu item of the index of the counter
            // (which should be equal to MainViewerActivity.currentRankingMenuItem) to the current
            // selected item on the physical navigation bar.
            menu.forEach {
                if ((MainViewerActivity.currentRankingMenuItem).toString() == (it.toString())) {
                    menu.getItem(counter).isChecked = true
                }
                counter ++
            }
        }
        // This is a listener for the navigation bar onClick.
        // When a menu item of the navigation bar is clicked, it sends the MenuItem to
        // the handler activity (MainViewerActivity) to set the adapter of this fragment's
        // resource layout to the correct adapter given the new menu item that should be displayed.
        root.nav_ranking_view.setOnNavigationItemSelectedListener {
            //Inputs 'it' as the MenuItem value as 'it' is the selected MenuItem.
            mListener!!.childRankingFragmentHandlerResponse(it, root, true)
            return@setOnNavigationItemSelectedListener true
        }

        root.lv_ranking.setOnItemClickListener { _, _, position, _ ->
            val rankingFragmentTransaction = this.fragmentManager!!.beginTransaction()
            teamDetailsFragmentArguments.putString(
                Constants.TEAM_NUMBER, convertToFilteredTeamsList(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value,
                    Constants.FIELDS_TO_BE_DISPLAYED_RANKING_NAVIGATION_BAR[MainViewerActivity.currentRankingMenuItem.toString()].toString(),
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.

        // If an error is thrown, the error means that we haven't implemented
        // RankingFragment.ChildRankingFragmentHandler in our handler activity.
        mListener = try {
            activity as ChildRankingFragmentHandler
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement ChildRankingFragmentHandler."
            )
        }
    }

    // When the fragment is detached, this fragment library function programmatically detaches the fragment
    // and 'resets' the MainViewerActivity-RankingFragment interface communicator.
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    // Interface to access the current selected menu item of the ranking navigation bar,
    // the current view of the fragment, and whether the global MainViewerActivity.currentRankingMenuItem
    // should be reset.
    interface ChildRankingFragmentHandler {
        fun childRankingFragmentHandlerResponse(menuItem: MenuItem, root: View, reset: Boolean)
    }
}