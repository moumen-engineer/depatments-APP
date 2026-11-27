package com.example.department_APP

import androidx.appcompat.app.AppCompatActivity

// Parent of the screens that have a back arrow in the top bar
abstract class BaseActivity : AppCompatActivity() {

    protected fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
