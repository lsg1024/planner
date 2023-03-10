package com.p3.planner.di

import android.content.Context
import androidx.room.Room
import com.p3.planner.database.ContactsDB
import com.p3.planner.database.ContactsEntity
import com.p3.planner.utils.Constants.CONTACTS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ContactsDB::class.java, CONTACTS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db:ContactsDB) = db.contactDao()


    @Provides
    fun provideEntity()=ContactsEntity()

}