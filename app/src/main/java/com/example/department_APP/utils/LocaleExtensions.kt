package com.example.department_APP.utils

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.example.department_APP.models.Department
import com.example.department_APP.models.Faculty

// True when the screen is currently displayed in Arabic
fun Context.isArabic(): Boolean =
    ConfigurationCompat.getLocales(resources.configuration)[0]?.language == "ar"

fun Faculty.localizedName(arabic: Boolean): String =
    if (arabic) nameAr else nameEn

fun Faculty.localizedDescription(arabic: Boolean): String =
    (if (arabic) descriptionAr else descriptionEn).orEmpty()

fun Department.localizedName(arabic: Boolean): String =
    if (arabic) nameAr else nameEn

fun Department.localizedDescription(arabic: Boolean): String =
    (if (arabic) descriptionAr else descriptionEn).orEmpty()

fun Department.localizedSpecialties(arabic: Boolean): String =
    (if (arabic) specialtiesAr ?: specialties else specialties).orEmpty()
