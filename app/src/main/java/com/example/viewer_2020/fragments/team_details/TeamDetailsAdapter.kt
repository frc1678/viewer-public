package com.example.viewer_2020.fragments.team_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.FragmentActivity
import com.example.viewer_2020.R
import com.example.viewer_2020.constants.Constants
import com.example.viewer_2020.constants.Translations
import com.example.viewer_2020.getTeamDataValue
import kotlinx.android.synthetic.main.team_details_cell.view.*
import java.lang.Float.parseFloat
import java.util.regex.Pattern

// Custom list adapter class for each list view of the six teams featured in every MatchDetails display.
// TODO implement a type 'Team' object parameter to access the team data for the team number.
class TeamDetailsAdapter(
    private val context: FragmentActivity,
    private val datapointsDisplayed: Map<String, ArrayList<String>>,
    private val currentSection: String,
    private val teamNumber: String
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // Return the size of the list of the data points to be displayed.
    override fun getCount(): Int {
        return datapointsDisplayed[currentSection]?.size!!
    }

    // Returns the specific data point given the position of the data point.
    override fun getItem(position: Int): Any {
        return datapointsDisplayed[currentSection]?.get(position) as Any
    }

    // Returns the position of the cell.
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Populate the elements of the custom cell.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val regex: Pattern = Pattern.compile("[0-9]+" + Regex.escape(".") + "[0-9]+")
        val rowView = inflater.inflate(R.layout.team_details_cell, parent, false)
        rowView.tv_datapoint_name.text =
            Translations.ACTUAL_TO_HUMAN_READABLE[datapointsDisplayed[currentSection]?.get(position)]
                ?: datapointsDisplayed[currentSection]?.get(position)
        rowView.tv_datapoint_value.text =
            if (regex.matcher(getTeamDataValue(
                    teamNumber,
                    datapointsDisplayed[currentSection]?.get(position)!!)).matches()) {
                ("%.1f").format(parseFloat(getTeamDataValue(teamNumber,
                    datapointsDisplayed[currentSection]?.get(position)!!))
                )
            } else {
                getTeamDataValue(teamNumber,
                    datapointsDisplayed[currentSection]?.get(position)!!
                )
            }
        return rowView
    }
}