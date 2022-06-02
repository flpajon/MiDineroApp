package ar.com.midinero.framework.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id_category: Long,
    val name: String
)
