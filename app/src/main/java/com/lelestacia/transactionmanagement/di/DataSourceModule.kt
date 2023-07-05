package com.lelestacia.transactionmanagement.di

import com.google.firebase.firestore.FirebaseFirestore
import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSource
import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideFirestoreDataSource(): FirestoreDataSource =
        FirestoreDataSourceImpl(
            service = FirebaseFirestore.getInstance()
        )
}