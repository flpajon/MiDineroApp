package ar.com.midinero.framework.room.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import ar.com.midinero.framework.room.entity.Category
import ar.com.midinero.framework.room.entity.Movement

data class CategoryWithMovements(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id_category",
        entityColumn = "id_category_movement"
    )
    val movements: List<Movement>
)