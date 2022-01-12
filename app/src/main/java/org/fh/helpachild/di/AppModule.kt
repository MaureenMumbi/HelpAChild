package org.fh.cfct.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import org.fh.cfct.db.AppDatabase
import org.fh.cfct.model.ChildDao
import org.fh.cfct.model.ChildResourcesDao
import org.fh.cfct.model.DonationDao
import org.fh.cfct.model.SponsorDao
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideChildDao(appDatabase: AppDatabase): ChildDao {
        return appDatabase.ChildDao()
    }

    @Provides
    fun provideSponsorDao(appDatabase: AppDatabase): SponsorDao {
        return appDatabase.SponsorDao()
    }

    @Provides
    fun provideDonorDao(appDatabase: AppDatabase): DonationDao {
        return appDatabase.DonationDao()
    }

    @Provides
    fun provideChildResourcesDao(appDatabase: AppDatabase): ChildResourcesDao {
        return appDatabase.ChildResourcesDao()
    }

}