package com.example.department_APP.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.department_APP.R

// Implicit intents: call, SMS, email and Google Maps. No permission is needed.
object ContactActions {

    private const val MAPS_PACKAGE = "com.google.android.apps.maps"

    // ACTION_DIAL opens the dialer with the number; the user presses "call"
    fun call(context: Context, phone: String) {
        start(context, Intent(Intent.ACTION_DIAL, Uri.parse("tel:${Uri.encode(phone)}")))
    }

    fun sms(context: Context, phone: String) {
        start(context, Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${Uri.encode(phone)}")))
    }

    fun email(context: Context, address: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        start(context, intent)
    }

    // Uses the coordinates if we have them, otherwise searches by text
    fun map(context: Context, latitude: Double?, longitude: Double?, searchText: String) {
        val query = if (latitude != null && longitude != null) {
            "$latitude,$longitude"
        } else {
            searchText
        }
        val uri = Uri.parse(
            "https://www.google.com/maps/search/?api=1&query=${Uri.encode(query)}"
        )

        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, uri).setPackage(MAPS_PACKAGE))
        } catch (e: ActivityNotFoundException) {
            // Google Maps is not installed: open the link in a browser
            start(context, Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun start(context: Context, intent: Intent) {
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, R.string.no_app_found, Toast.LENGTH_SHORT).show()
        }
    }
}
