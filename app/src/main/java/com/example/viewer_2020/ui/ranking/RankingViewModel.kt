/*
* RankingViewModel.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Fragment view handler for the RankingFragment fragment.
//The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
class RankingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ranking frag"
    }
    val text: LiveData<String> = _text
}