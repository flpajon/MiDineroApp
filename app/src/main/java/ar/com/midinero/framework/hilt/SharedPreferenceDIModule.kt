package ar.com.midinero.framework.hilt

import android.content.Context
import android.content.SharedPreferences
import ar.com.midinero.core.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class SharedPreferenceDIModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constants.IS_FIRS_TIME,
            Context.MODE_PRIVATE
        )
}