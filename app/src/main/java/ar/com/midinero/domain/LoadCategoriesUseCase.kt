package ar.com.midinero.domain

import ar.com.midinero.data.repository.CategoryRepository
import javax.inject.Inject

class LoadCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend fun loadCategories(): Boolean = repository.loadCategories()
}