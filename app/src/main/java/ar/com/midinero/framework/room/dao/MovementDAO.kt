package ar.com.midinero.framework.room.dao

import androidx.room.*
import ar.com.midinero.framework.room.entity.Movement
import ar.com.midinero.framework.room.entity.relationship.CategoryWithMovements

@Dao
interface MovementDAO {

    @Transaction
    @Query("SELECT * FROM Category")
    fun getCategoryMovements(): List<CategoryWithMovements>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovements(vararg movements: Movement)

}