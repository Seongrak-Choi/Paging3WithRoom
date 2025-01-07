package com.fromu.paging3androomtoyproject.di

import android.content.Context
import androidx.room.Room
import com.fromu.paging3androomtoyproject.data.local.AppDatabase
import com.fromu.paging3androomtoyproject.data.local.dao.LikedItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideLikedItemDao(appDatabase: AppDatabase): LikedItemDao {
        return appDatabase.likedItemDao()
    }
}