package com.fromu.paging3androomtoyproject.di

import com.fromu.paging3androomtoyproject.data.datasource.remote.PhotoDataSource
import com.fromu.paging3androomtoyproject.data.repository.PhotoRepoImpl
import com.fromu.paging3androomtoyproject.domain.repository.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepo(photoDataSource: PhotoDataSource): PhotoRepo {
        return PhotoRepoImpl(photoDataSource)
    }
}