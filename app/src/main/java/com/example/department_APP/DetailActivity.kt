package com.example.department_APP

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.department_APP.database.DatabaseHelper
import com.example.department_APP.utils.ContactActions
import com.example.department_APP.utils.isArabic
import com.example.department_APP.utils.localizedDescription
import com.example.department_APP.utils.localizedName
import com.example.department_APP.utils.localizedSpecialties

// Shows a faculty, or a department when EXTRA_DEPARTMENT_ID is given
class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_FACULTY_ID = "extra_faculty_id"
        const val EXTRA_DEPARTMENT_ID = "extra_department_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = getString(R.string.details)
        showBackButton()

        val facultyId = intent.getIntExtra(EXTRA_FACULTY_ID, -1)
        val departmentId = intent.getIntExtra(EXTRA_DEPARTMENT_ID, -1)

        val database = DatabaseHelper(this)
        val faculty = database.getAllFaculties().find { it.id == facultyId }
        val department = database.getDepartmentsByFacultyId(facultyId)
            .find { it.id == departmentId }
        database.close()

        if (faculty == null) {
            finish()
            return
        }

        val arabic = isArabic()
        val name = department?.localizedName(arabic) ?: faculty.localizedName(arabic)
        val description = department?.localizedDescription(arabic)
            ?: faculty.localizedDescription(arabic)
        val specialties = department?.localizedSpecialties(arabic).orEmpty()

        findViewById<TextView>(R.id.tvName).text = name
        findViewById<TextView>(R.id.tvDescription).text = description

        val specialtiesLabel = findViewById<TextView>(R.id.tvSpecialtiesLabel)
        val specialtiesText = findViewById<TextView>(R.id.tvSpecialties)
        specialtiesText.text = specialties
        // Hide the section for faculties and when there is nothing to show
        val visibility = if (specialties.isBlank()) View.GONE else View.VISIBLE
        specialtiesLabel.visibility = visibility
        specialtiesText.visibility = visibility

        // A department without its own contact uses the contact of its faculty
        val phone = department?.phone ?: faculty.phone
        val email = department?.email ?: faculty.email

        setupButton(R.id.btnCall, !phone.isNullOrBlank()) {
            ContactActions.call(this, phone.orEmpty())
        }
        setupButton(R.id.btnSms, !phone.isNullOrBlank()) {
            ContactActions.sms(this, phone.orEmpty())
        }
        setupButton(R.id.btnEmail, !email.isNullOrBlank()) {
            ContactActions.email(this, email.orEmpty(), name)
        }
        setupButton(R.id.btnMap, true) {
            val searchText = listOfNotNull(faculty.nameEn, faculty.address).joinToString(", ")
            ContactActions.map(this, faculty.latitude, faculty.longitude, searchText)
        }
    }

    // A button is greyed out when the information is missing
    private fun setupButton(id: Int, enabled: Boolean, action: () -> Unit) {
        val button = findViewById<Button>(id)
        button.isEnabled = enabled
        button.alpha = if (enabled) 1f else 0.4f
        button.setOnClickListener { action() }
    }
}
