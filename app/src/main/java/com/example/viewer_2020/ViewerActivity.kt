package com.example.viewer_2020

import android.app.ActivityOptions
import android.content.Intent
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

//Super class of all activity based classes for this project.
//Used to implement class mechanisms that all activities should comprise of.
open class ViewerActivity : AppCompatActivity() {

    //When the back press is held down, this function will confirm the long click and then 'restart'
    //the app by sending it to the mode collection activity and resetting the mode.
    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intentToMainViewerActivity()
        }
        return super.onKeyLongPress(keyCode, event)
    }

    //Begins the intent to the main activity when the long back press is clicked.
    private fun intentToMainViewerActivity() {
        startActivity(
            Intent(this, MainViewerActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}