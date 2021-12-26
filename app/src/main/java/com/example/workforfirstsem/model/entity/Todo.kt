package com.example.workforfirstsem.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.workforfirstsem.utils.Converter
import java.util.*

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val desc: String?,
    val date: Date?,
    val latitude: String?,
    val longitude: String?,
) {
    constructor(title: String?, desc: String?, date: Date?, latitude: String?, longitude: String?) :
            this(0, title, desc, date, latitude, longitude)
}
