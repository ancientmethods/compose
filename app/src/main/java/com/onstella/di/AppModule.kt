package com.onstella.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.onstella.MyApplication
import com.onstella.database.AppDao
import com.onstella.database.AppDatabase
import com.onstella.database.DataRepository
import com.onstella.utils.Authenticator
import com.onstella.utils.FirebaseAuthentication
import com.onstella.utils.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * module with singleton that survice the entire application lifecycle
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): MyApplication {
        return context as MyApplication
    }

    @Provides
    @Singleton
    fun provideSharedPrefences(application: MyApplication): SharedPreferences {
        return application.getSharedPreferences("on_stella", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefencesManager(sharedPreferences: SharedPreferences): PreferencesManager {
        return (PreferencesManager(sharedPreferences))
    }

    @Provides
    @Singleton
    fun provideDatabase(application: MyApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "Stella.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase): AppDao {
        return database.appDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: AppDao): DataRepository {
        return DataRepository(dao)
    }


    @Provides
    @Singleton
    fun provideAuthenticator(): Authenticator {
        return FirebaseAuthentication()
    }
}