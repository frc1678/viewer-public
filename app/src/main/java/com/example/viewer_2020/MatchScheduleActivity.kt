/*
* MatchScheduleActivity.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.match_schedule.*

// Displays the match schedule of the given event.
// A huge list of every match where each cell includes all the match's teams and the match's predicted score.
class MatchScheduleActivity : AppCompatActivity() {

    // Creates the on click listeners for each XML element with an onClick implementation.
    private fun initializeClickListeners() {
        next.setOnLongClickListener {
            startNextActivity()
            return@setOnLongClickListener true
        }
    }

    // Function whose purpose is to begin the given activity when called.
    private fun startNextActivity() {
        startActivity(Intent(this, MatchDetailsActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_schedule)

        initializeClickListeners()
    }
}