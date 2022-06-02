package ar.com.midinero.data.datasource

interface CategoryDataSource {
    suspend fun isFirstTime(): Boolean
    suspend fun loadCategories()

}