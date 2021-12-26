package com.example.workforfirstsem.model.dao

import androidx.room.*
import com.example.workforfirstsem.model.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: Int): Todo

    @Insert
    suspend fun save(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(todo: Todo)
}
