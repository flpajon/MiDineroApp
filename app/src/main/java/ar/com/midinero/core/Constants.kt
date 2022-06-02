package ar.com.midinero.core

import ar.com.midinero.framework.room.entity.Category

object Constants {
    val IS_FIRS_TIME: String = "IS_FIRS_TIME"

    val categories = listOf<Category>(
        Category(1L,"Supermercado"),
        Category(2L,"Carniceria"),
        Category(3L,"Verduleria"),
        Category(4L,"Servicio"),
        Category(5L,"Ocio"),
        Category(6L,"Alquiler"),
        Category(7L,"Impuesto"),
        Category(8L,"Tasas"),
        Category(9L,"Seguro"),
        Category(10L,"Varios"),
    )
}