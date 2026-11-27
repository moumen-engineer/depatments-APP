package com.example.department_APP

import android.content.Intent
import android.os.Bundle
import com.example.department_APP.adapters.ListItem
import com.example.department_APP.utils.isArabic
import com.example.department_APP.utils.localizedDescription
import com.example.department_APP.utils.localizedName

// Click: departments of the faculty. Long press: details of the faculty.
class FacultyListActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_list)
        title = getString(R.string.faculties)
        showBackButton()
        setupList()
    }

    override fun loadItems(): List<ListItem> {
        val arabic = isArabic()
        return database.getAllFaculties().map { faculty ->
            ListItem(
                id = faculty.id,
                name = faculty.localizedName(arabic),
                description = faculty.localizedDescription(arabic),
                // Search works with the English and the Arabic name
                searchText = "${faculty.nameEn} ${faculty.nameAr}"
            )
        }
    }

    override fun onItemSelected(id: Int) {
        val intent = Intent(this, DepartmentListActivity::class.java)
        intent.putExtra(DepartmentListActivity.EXTRA_FACULTY_ID, id)
        startActivity(intent)
    }

    override fun onItemLongPressed(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FACULTY_ID, id)
        startActivity(intent)
    }
}
