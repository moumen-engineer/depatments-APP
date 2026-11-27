package com.example.department_APP

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.department_APP.utils.AppPreferences
import com.example.department_APP.utils.isArabic

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = getString(R.string.settings)
        showBackButton()

        val preferences = AppPreferences(this)
        val languageGroup = findViewById<RadioGroup>(R.id.rgLanguage)
        val displayGroup = findViewById<RadioGroup>(R.id.rgDisplay)

        // Show the current choices
        languageGroup.check(if (isArabic()) R.id.rbArabic else R.id.rbEnglish)
        displayGroup.check(if (preferences.isGridView) R.id.rbGrid else R.id.rbList)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            preferences.isGridView = displayGroup.checkedRadioButtonId == R.id.rbGrid

            val language =
                if (languageGroup.checkedRadioButtonId == R.id.rbArabic) "ar" else "en"
            // Changes the language of the whole app and switches to right-to-left
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language)
            )
            finish()
        }
    }
}
