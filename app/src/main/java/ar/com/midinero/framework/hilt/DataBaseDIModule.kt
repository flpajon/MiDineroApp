package ar.com.midinero.framework.hilt

import android.content.Context
import androidx.room.Room
import ar.com.midinero.framework.room.DataBase
import ar.com.midinero.framework.room.dao.CategoryDAO
import ar.com.midinero.framework.room.dao.MovementDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseDIModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DataBase = Room.databaseBuilder(
        context,
        DataBase::class.java, "mi_dinero_db"
    ).build()

    @Provides
    fun provideMovementDao(db: DataBase): MovementDAO = db.movementDao()

    @Provides
    fun provideCategoryDao(db: DataBase): CategoryDAO = db.categoryDao()
}