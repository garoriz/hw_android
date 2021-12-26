package com.example.workforfirstsem.utils

import android.icu.util.Calendar
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
