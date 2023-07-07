package com.lelestacia.transactionmanagement.di

import android.content.Context
import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSource
import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import com.lelestacia.transactionmanagement.data.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTransactionRepository(
        firestoreDataSource: FirestoreDataSource,
        @ApplicationContext context: Context
    ): TransactionRepository =
        TransactionRepositoryImpl(
            dataSource = firestoreDataSource,
            context = context
        )
}