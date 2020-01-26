/*
* RankingFragment.kt
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
import kotlinx.android.synthetic.main.fragment_ranking.view.*

//The fragment of the ranking lists 'view' that is one of the options of the navigation bar.
class RankingFragment : Fragment() {

    private lateinit var rankingViewModel: RankingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rankingViewModel =
            ViewModelProviders.of(this).get(RankingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)
        rankingViewModel.text.observe(this, Observer {
            root.text_ranking.text = it
        })
        return root
    }
}