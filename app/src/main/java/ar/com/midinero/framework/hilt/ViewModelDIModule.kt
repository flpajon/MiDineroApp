package ar.com.midinero.framework.hilt

import ar.com.midinero.data.datasource.CategoryDataSourceImpl
import ar.com.midinero.data.datasource.UserDataSourceImpl
import ar.com.midinero.data.repository.CategoryRepository
import ar.com.midinero.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelDIModule {

    @Provides
    fun provideUserRepository(
        dataSource: UserDataSourceImpl
    ) = UserRepository(dataSource)

    @Provides
    fun provideCategoryRepository(dataSource: CategoryDataSourceImpl) =
        CategoryRepository(dataSource)

}