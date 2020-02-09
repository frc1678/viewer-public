/*
* MainViewerActivity.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_ranking.view.*

// Main activity class that handles the dual fragment view.
class MainViewerActivity : AppCompatActivity(),
    RankingFragment.ChildRankingFragmentHandler {

    companion object {
        var currentRankingMenuItem: MenuItem? = null
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

    // Requests storage permissions if they are not already given.
    private fun verifyStoragePermissions() {
        val permission =
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                1
            )
        }
    }

    // Interface to retrieve the selected MenuItem, the view of the fragment, and whether
    // the local currentRankingMenuItem variable should be overridden or not.
    // If this function is called and currentRankingMenuItem is null (AKA the first time
    // the fragment is accessed), then it wouldn't want to reset the local variable
    // as that would create a loop because the 'reset = false' function is run at the first
    // instance of the RankingFragment onCreateView method, meaning that it would reset
    // the currentRankingMenuItem through this function if its reset value were true.
    override fun childRankingFragmentHandlerResponse(menuItem: MenuItem, root: View, reset: Boolean) {
        if (currentRankingMenuItem != menuItem && reset) currentRankingMenuItem = menuItem
        root.lv_ranking.adapter = RankingListAdapter(this, menuItem.toString(), true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyStoragePermissions()
        setupNavigationController(R.id.nav_host_fragment)

        // Interface to access the DatabaseReference -> CompetitionObject object that
        // should be an exact replica of every WANTED data value from MongoDB.

        // 'response' is a CompetitionObject, so you should be able to access whatever datapoint
        // you want by referencing response. Example: response.raw.qr[0] -> specified value in database.
        val callback: MongoDatabaseListenerUtil.Callback<DatabaseReference.CompetitionObject> = object :
            MongoDatabaseListenerUtil.Callback<DatabaseReference.CompetitionObject> {
                override fun execute(response: DatabaseReference.CompetitionObject) {
                    //todo 'response' is a CompetitionObject. Example: response.[raw].[qr][0] will return the specified value.
            }
        }
        MongoDatabaseListenerUtil().getCompetitionDocument(callback)
    }
}