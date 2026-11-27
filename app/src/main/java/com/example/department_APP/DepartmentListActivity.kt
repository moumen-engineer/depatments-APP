package com.example.department_APP

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.department_APP.adapters.ListItem
import com.example.department_APP.utils.isArabic
import com.example.department_APP.utils.localizedDescription
import com.example.department_APP.utils.localizedName

// Departments of one faculty. Tap the blue header to see the faculty details.
class DepartmentListActivity : BaseListActivity() {

    companion object {
        const val EXTRA_FACULTY_ID = "extra_faculty_id"
    }

    private var facultyId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department_list)
        title = getString(R.string.departments)
        showBackButton()
        setupList()

        facultyId = intent.getIntExtra(EXTRA_FACULTY_ID, -1)
        val faculty = database.getAllFaculties().find { it.id == facultyId }
        if (faculty == null) {
            finish()
            return
        }

        val header = findViewById<TextView>(R.id.tvFacultyName)
        header.text = getString(R.string.faculty_header, faculty.localizedName(isArabic()))
        header.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FACULTY_ID, facultyId)
            startActivity(intent)
        }

        if (database.getDepartmentsByFacultyId(facultyId).isEmpty()) {
            Toast.makeText(this, R.string.no_departments, Toast.LENGTH_LONG).show()
        }
    }

    override fun loadItems(): List<ListItem> {
        val arabic = isArabic()
        return database.getDepartmentsByFacultyId(facultyId).map { department ->
            ListItem(
                id = department.id,
                name = department.localizedName(arabic),
                description = department.localizedDescription(arabic),
                // Search by name or by specialty, in both languages
                searchText = listOfNotNull(
                    department.nameEn,
                    department.nameAr,
                    department.specialties,
                    department.specialtiesAr
                ).joinToString(" ")
            )
        }
    }

    override fun onItemSelected(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FACULTY_ID, facultyId)
        intent.putExtra(DetailActivity.EXTRA_DEPARTMENT_ID, id)
        startActivity(intent)
    }
}
