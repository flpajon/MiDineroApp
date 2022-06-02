package ar.com.midinero.data.datasource

import android.content.SharedPreferences
import ar.com.midinero.core.Constants
import ar.com.midinero.framework.room.dao.CategoryDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryDataSourceImpl @Inject constructor(
    private val sp: SharedPreferences,
    private val categoryDao: CategoryDAO
) :
    CategoryDataSource {
    override suspend fun isFirstTime(): Boolean = withContext(Dispatchers.IO) {
        val isFirtTime = sp.getBoolean(Constants.IS_FIRS_TIME, true)
        if (isFirtTime){
            sp.edit().putBoolean(Constants.IS_FIRS_TIME, false).apply()
        }
        isFirtTime
    }

    override suspend fun loadCategories() = withContext(Dispatchers.IO) {
        Constants.categories.forEach(){ category ->
            categoryDao.insertCategory(category)
        }
    }
}