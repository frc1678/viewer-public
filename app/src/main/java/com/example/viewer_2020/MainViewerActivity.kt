/*
* MainViewerActivity.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.constants.Translations
import com.example.viewer_2020.data.*
import com.example.viewer_2020.fragments.ranking.RankingListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import java.io.File

// Main activity class that handles the dual fragment view.
class MainViewerActivity : ViewerActivity(),
    RankingFragment.ChildRankingFragmentHandler {

    companion object {
        var currentRankingMenuItem: MenuItem? = null
        var databaseReference: DatabaseReference.CompetitionObject? = null
        var teamCache: HashMap<String, Team> = HashMap()
    }

    // Populates the menu items and fragment items with the corresponding fragment IDs.
    private fun setupNavigationController(host: Int) {
        // This is where you put the main activity's menu options. The ranking navigation bar
        // does not have a navigation controller connected to a fragment because its only usage
        // is to return a position value to set the correct collection of data for the ranking adapter.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_match_schedule, R.id.navigation_ranking))
        setupActionBarWithNavController(findNavController(host), appBarConfiguration)
        nav_view.setupWithNavController(findNavController(host))
    }

    fun verifyCSVFileExists(file: String) {
        val csvFile = File( "/storage/emulated/0/${Environment.DIRECTORY_DOWNLOADS}/$file")
        if (!csvFile.exists()) {
            AlertDialog.Builder(this).setMessage("There is no CSV file on this device").show()
        }
    }

    // Interface to retrieve the selected ranking fragment MenuItem, the view of the ranking fragment,
    // and whether the local currentRankingMenuItem variable should be overridden or not.
    // If this function is called and currentRankingMenuItem is null (AKA the first time
    // the fragment is accessed), then it wouldn't want to reset the local variable
    // as that would create a loop because the 'reset = false' function is run at the first
    // instance of the RankingFragment onCreateView method, meaning that it would reset
    // the currentRankingMenuItem through this function if its reset value were true.
    override fun childRankingFragmentHandlerResponse(menuItem: MenuItem, root: View, reset: Boolean) {
        if (currentRankingMenuItem != menuItem && reset) currentRankingMenuItem = menuItem
        else if (!reset) currentRankingMenuItem = menuItem
        root.lv_ranking.adapter =
            RankingListAdapter(
                this,
                menuItem.toString(),
                convertToFilteredTeamsList(
                    Constants.PROCESSED_OBJECT.CALCULATED_PREDICTED_TEAM.value,
                    Constants.FIELDS_TO_BE_DISPLAYED_RANKING_NAVIGATION_BAR[menuItem.toString()].toString(),
                    csvFileRead("team_list.csv", false)[0].trim().split(" ")
                )
            )
        root.tv_team_number.text = getString(R.string.team_number_text)
        when (menuItem.toString()) {
            Constants.FIELDS_TO_BE_DISPLAYED_RANKING_NAVIGATION_BAR.keys.toTypedArray()[0] -> {
                root.tv_datapoint_one.text =
                    Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_SECTION_ONE_RANKING[0]]
                root.tv_datapoint_two.text =
                    Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_SECTION_ONE_RANKING[1]]
                root.tv_datapoint_three.text =
                    Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_SECTION_ONE_RANKING[2]]
            }
            Constants.FIELDS_TO_BE_DISPLAYED_RANKING_NAVIGATION_BAR.keys.toTypedArray()[1] -> {
                root.tv_datapoint_one.text =
                    Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_SECTION_TWO_RANKING[0]]
                root.tv_datapoint_two.text =
                    Translations.ACTUAL_TO_HUMAN_READABLE[Constants.FIELDS_TO_BE_DISPLAYED_SECTION_TWO_RANKING[1]]
                root.tv_datapoint_three.text = Constants.EMPTY_CHARACTER
            }
        }
    }

    // Override the onBackPressed to disable the back button as everything is inside fragments.
    override fun onBackPressed() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        verifyCSVFileExists("match_schedule.csv")
        setupNavigationController(R.id.nav_host_fragment)
    }
}