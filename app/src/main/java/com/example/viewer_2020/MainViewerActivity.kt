/*
* MainViewerActivity.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

//Main activity class that handles the dual fragment view.
class MainViewerActivity : AppCompatActivity() {

    //Populates the menu items and fragment items with the corresponding fragment IDs.
    private fun setupNavigationController(host: Int) {
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_match_schedule, R.id.navigation_ranking))
        setupActionBarWithNavController(findNavController(host), appBarConfiguration)
        nav_view.setupWithNavController(findNavController(host))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationController(R.id.nav_host_fragment)
    }
}
