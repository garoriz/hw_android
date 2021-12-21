package com.example.workforfirstsem.model.dao

import androidx.room.*
import com.example.workforfirstsem.model.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Int): Todo

    @Insert
    fun save(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Query("DELETE FROM todo")
    fun deleteAll()

    @Delete
    fun delete(todo: Todo)
}
