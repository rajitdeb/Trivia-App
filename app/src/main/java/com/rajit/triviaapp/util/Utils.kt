package com.rajit.triviaapp.util

import android.util.Log
import android.widget.Toast

object Utils {

    private const val TAG = "Utils"

    /**
     * This function validates if the @name entered is in the specified range and is not empty
     */
    fun validatePlayerNameEdt(name: String): Boolean {
        // if name is not empty
        return if (name.trim().isNotEmpty()) {
            // check if the name length is in range, return true or false
            name.length in 3..32
        } else {
            // if name is empty, return false
            false
        }
    }

    /**
     * This function formats the @name and capitalizes the first letter of each word
     * E.g. ramesh -> Ramesh, Ramesh gill -> Ramesh Gill, ramesh ram -> Ramesh Ram
     */
    fun convertToNormalCase(name: String): String {
        return if(name.contains(" ")){
            val firstName: String = name.substringBefore(" ")
            val lName: String = name.substringAfter(" ")
            Log.d(TAG, "convertToNormalCase: First: $firstName, Last: $lName")
            firstName.replaceFirstChar { it.uppercase() } + " " + lName.replaceFirstChar { it.uppercase() }

        } else {
            name.replaceFirst(
                oldChar = name[0],
                newChar = name[0].uppercase()[0],
                ignoreCase = false
            )
        }
    }

}