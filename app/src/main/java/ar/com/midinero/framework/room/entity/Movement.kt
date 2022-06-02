package ar.com.midinero.framework.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ar.com.midinero.core.DateConverter
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Movement(
    @PrimaryKey(autoGenerate = true)
    val id_movement: Long,
    val amount: Double,
    val date: Date,
    val uid: String,
    val proof_of_payment: String,
    val id_category_movement: Long
)