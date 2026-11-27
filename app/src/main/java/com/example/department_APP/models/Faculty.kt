package com.example.department_APP.models

data class Faculty(
    val id: Int = 0,
    val nameEn: String,
    val nameAr: String,
    val descriptionEn: String? = null,
    val descriptionAr: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
