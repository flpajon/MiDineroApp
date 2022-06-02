package ar.com.midinero.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.com.midinero.framework.room.dao.CategoryDAO
import ar.com.midinero.framework.room.dao.MovementDAO
import ar.com.midinero.framework.room.entity.Category
import ar.com.midinero.framework.room.entity.Movement

@Database(entities = [Movement::class, Category::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun movementDao(): MovementDAO
    abstract fun categoryDao():CategoryDAO
}