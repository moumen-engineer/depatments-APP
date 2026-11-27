package com.example.department_APP.models

data class Department(
    val id: Int = 0,
    val facultyId: Int,
    val nameEn: String,
    val nameAr: String,
    val descriptionEn: String? = null,
    val descriptionAr: String? = null,
    val specialties: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val specialtiesAr: String? = null
)
