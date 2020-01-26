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
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_match_schedule.view.*

//The fragment of the match schedule 'view' that is one of the options of the navigation bar.
class MatchScheduleFragment : Fragment() {

    private lateinit var matchScheduleViewModel: MatchScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        matchScheduleViewModel =
            ViewModelProviders.of(this).get(MatchScheduleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_match_schedule, container, false)
        matchScheduleViewModel.text.observe(this, Observer {
            root.text_match_schedule.text = it
        })
        return root
    }
}