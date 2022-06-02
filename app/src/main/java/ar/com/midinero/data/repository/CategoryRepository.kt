package ar.com.midinero.data.repository

import ar.com.midinero.data.datasource.CategoryDataSource
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val dataSource: CategoryDataSource) {
    suspend fun loadCategories(): Boolean{
        return if (dataSource.isFirstTime()){
            dataSource.loadCategories()
            true
        }else{
            false
        }
    }
}