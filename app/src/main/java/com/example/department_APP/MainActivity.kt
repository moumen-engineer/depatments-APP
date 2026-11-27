package com.example.department_APP

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.department_APP.database.DatabaseHelper
import com.example.department_APP.database.DatabaseSeeder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set the title in code so that it follows the chosen language
        title = getString(R.string.app_name)

        // Fill the database the first time the app runs
        val database = DatabaseHelper(this)
        DatabaseSeeder.seedIfEmpty(database)
        database.close()

        findViewById<Button>(R.id.btnFaculties).setOnClickListener {
            startActivity(Intent(this, FacultyListActivity::class.java))
        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
