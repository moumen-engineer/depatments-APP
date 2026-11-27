package com.example.department_APP.utils

import android.content.Context

// Saves the display style chosen by the user (ListView or GridView).
// The language is saved automatically by AppCompat (see AndroidManifest.xml).
class AppPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("umbb_prefs", Context.MODE_PRIVATE)

    var isGridView: Boolean
        get() = prefs.getBoolean("grid_view", false)
        set(value) {
            prefs.edit().putBoolean("grid_view", value).apply()
        }
}
