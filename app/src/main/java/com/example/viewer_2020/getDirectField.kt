/*
* getDirectField.kt
* viewer
*
* Created on 2/22/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.example.viewer_2020.constants.Constants

// Returns the value of the datapoint provided by findFieldInInheritedFields.
@Throws(Exception::class)
fun getDirectField(`object`: Any, field: String): Any {
    return try {
        if (`object` is List<*>) {
            `object`[field.toInt()]!!
        } else {
            findFieldInInheritedFields(`object`::class.java, field)[`object`]!!
        }
    } catch (e: Exception) {
        return Constants.NULL_CHARACTER
    }
}