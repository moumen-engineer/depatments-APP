package com.example.department_APP.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import com.example.department_APP.models.Faculty
import com.example.department_APP.models.Department

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "umbb_guide.db"
        private const val DATABASE_VERSION = 2

        const val TABLE_FACULTIES = "faculties"
        const val TABLE_DEPARTMENTS = "departments"
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {

        // Faculties table
        val createFacultiesTable = """
            CREATE TABLE $TABLE_FACULTIES (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name_en TEXT NOT NULL,
                name_ar TEXT NOT NULL,
                description_en TEXT,
                description_ar TEXT,
                address TEXT,
                phone TEXT,
                email TEXT,
                latitude REAL,
                longitude REAL
            )
        """.trimIndent()

        // Departments table
        val createDepartmentsTable = """
            CREATE TABLE $TABLE_DEPARTMENTS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                faculty_id INTEGER NOT NULL,
                name_en TEXT NOT NULL,
                name_ar TEXT NOT NULL,
                description_en TEXT,
                description_ar TEXT,
                specialties TEXT,
                specialties_ar TEXT,
                phone TEXT,
                email TEXT,
                FOREIGN KEY (faculty_id)
                    REFERENCES $TABLE_FACULTIES(id)
                    ON DELETE CASCADE
            )
        """.trimIndent()

        db.execSQL(createFacultiesTable)
        db.execSQL(createDepartmentsTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DEPARTMENTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FACULTIES")
        onCreate(db)
    }

    // INSERT A FACULTY
    fun insertFaculty(faculty: Faculty): Long {

        val db = writableDatabase
        val values = ContentValues()

        values.put("name_en", faculty.nameEn)
        values.put("name_ar", faculty.nameAr)
        values.put("description_en", faculty.descriptionEn)
        values.put("description_ar", faculty.descriptionAr)
        values.put("address", faculty.address)
        values.put("phone", faculty.phone)
        values.put("email", faculty.email)
        values.put("latitude", faculty.latitude)
        values.put("longitude", faculty.longitude)

        return db.insert(TABLE_FACULTIES, null, values)
    }

    // GET ALL FACULTIES
    fun getAllFaculties(): ArrayList<Faculty> {

        val faculties = ArrayList<Faculty>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_FACULTIES",
            null
        )

        cursor.use {
            while (it.moveToNext()) {

                val faculty = Faculty(
                    id = it.getInt(it.getColumnIndexOrThrow("id")),
                    nameEn = it.getString(it.getColumnIndexOrThrow("name_en")),
                    nameAr = it.getString(it.getColumnIndexOrThrow("name_ar")),
                    descriptionEn = it.getString(it.getColumnIndexOrThrow("description_en")),
                    descriptionAr = it.getString(it.getColumnIndexOrThrow("description_ar")),
                    address = it.getString(it.getColumnIndexOrThrow("address")),
                    phone = it.getString(it.getColumnIndexOrThrow("phone")),
                    email = it.getString(it.getColumnIndexOrThrow("email")),
                    latitude = if (it.isNull(it.getColumnIndexOrThrow("latitude"))) null
                    else it.getDouble(it.getColumnIndexOrThrow("latitude")),
                    longitude = if (it.isNull(it.getColumnIndexOrThrow("longitude"))) null
                    else it.getDouble(it.getColumnIndexOrThrow("longitude"))
                )

                faculties.add(faculty)
            }
        }

        return faculties
    }

    fun insertDepartment(department: Department): Long {

        val db = writableDatabase

        val values = ContentValues().apply {
            put("faculty_id", department.facultyId)
            put("name_en", department.nameEn)
            put("name_ar", department.nameAr)
            put("description_en", department.descriptionEn)
            put("description_ar", department.descriptionAr)
            put("specialties", department.specialties)
            put("specialties_ar", department.specialtiesAr)
            put("phone", department.phone)
            put("email", department.email)
        }

        val result = db.insert("departments", null, values)

        return result
    }

    fun getDepartmentsByFacultyId(facultyId: Int): ArrayList<Department> {

        val departments = ArrayList<Department>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM departments WHERE faculty_id = ?",
            arrayOf(facultyId.toString())
        )

        cursor.use {

            while (it.moveToNext()) {

                val department = Department(
                    id = it.getInt(it.getColumnIndexOrThrow("id")),
                    facultyId = it.getInt(it.getColumnIndexOrThrow("faculty_id")),
                    nameEn = it.getString(it.getColumnIndexOrThrow("name_en")),
                    nameAr = it.getString(it.getColumnIndexOrThrow("name_ar")),
                    descriptionEn = it.getString(it.getColumnIndexOrThrow("description_en")),
                    descriptionAr = it.getString(it.getColumnIndexOrThrow("description_ar")),
                    specialties = it.getString(it.getColumnIndexOrThrow("specialties")),
                    phone = it.getString(it.getColumnIndexOrThrow("phone")),
                    email = it.getString(it.getColumnIndexOrThrow("email")),
                    specialtiesAr = it.getString(it.getColumnIndexOrThrow("specialties_ar"))
                )

                departments.add(department)
            }
        }

        return departments
    }

    fun updateFaculty(faculty: Faculty): Int {

        val db = writableDatabase

        val values = ContentValues().apply {
            put("name_en", faculty.nameEn)
            put("name_ar", faculty.nameAr)
            put("description_en", faculty.descriptionEn)
            put("description_ar", faculty.descriptionAr)
            put("address", faculty.address)
            put("phone", faculty.phone)
            put("email", faculty.email)
            put("latitude", faculty.latitude)
            put("longitude", faculty.longitude)
        }

        val result = db.update(
            "faculties",
            values,
            "id = ?",
            arrayOf(faculty.id.toString())
        )

        return result
    }

    fun deleteFaculty(id: Int): Int {

        val db = writableDatabase

        val result = db.delete(
            "faculties",
            "id = ?",
            arrayOf(id.toString())
        )

        return result
    }

    fun updateDepartment(department: Department): Int {

        val db = writableDatabase

        val values = ContentValues().apply {
            put("faculty_id", department.facultyId)
            put("name_en", department.nameEn)
            put("name_ar", department.nameAr)
            put("description_en", department.descriptionEn)
            put("description_ar", department.descriptionAr)
            put("specialties", department.specialties)
            put("specialties_ar", department.specialtiesAr)
            put("phone", department.phone)
            put("email", department.email)
        }

        val result = db.update(
            "departments",
            values,
            "id = ?",
            arrayOf(department.id.toString())
        )

        return result
    }

    fun deleteDepartment(id: Int): Int {

        val db = writableDatabase

        val result = db.delete(
            "departments",
            "id = ?",
            arrayOf(id.toString())
        )

        return result
    }



}
