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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

    //Requests storage permissions if they are not already given.
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyStoragePermissions()
        setupNavigationController(R.id.nav_host_fragment)

        val callback: MongoDatabaseListenerUtil.Callback<DatabaseReference.CompetitionObject> = object :
            MongoDatabaseListenerUtil.Callback<DatabaseReference.CompetitionObject> {
                override fun execute(response: DatabaseReference.CompetitionObject) {
                    //todo 'response' is a CompetitionObject. Example: response.[raw].[qr][0] will return the specified value.
            }
        }
        MongoDatabaseListenerUtil().getCompetitionDocument(callback)
    }
}