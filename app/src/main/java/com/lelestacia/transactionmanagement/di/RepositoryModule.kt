package com.lelestacia.transactionmanagement.di

import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSource
import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import com.lelestacia.transactionmanagement.data.repository.TransactionRepositoryImpl
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
    fun provideTransactionRepository(
        firestoreDataSource: FirestoreDataSource
    ): TransactionRepository =
        TransactionRepositoryImpl(
            dataSource = firestoreDataSource
        )
}