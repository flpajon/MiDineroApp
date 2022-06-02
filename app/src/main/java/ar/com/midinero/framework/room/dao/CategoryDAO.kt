package ar.com.midinero.framework.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.com.midinero.framework.room.entity.Category

@Dao
interface CategoryDAO {

    @Query("SELECT * FROM Category")
    fun loadAllCategories(): List<Category>

    @Insert
    fun insertCategory(vararg categories: Category)
}