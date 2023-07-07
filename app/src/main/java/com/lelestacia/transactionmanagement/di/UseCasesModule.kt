package com.lelestacia.transactionmanagement.di

import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import com.lelestacia.transactionmanagement.domain.usecases.add_transaction.AddTransactionUseCases
import com.lelestacia.transactionmanagement.domain.usecases.add_transaction.AddTransactionUseCasesImpl
import com.lelestacia.transactionmanagement.domain.usecases.dashboard.DashboardUseCases
import com.lelestacia.transactionmanagement.domain.usecases.dashboard.DashboardUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideDashboardUseCases(
        repository: TransactionRepository
    ): DashboardUseCases =
        DashboardUseCasesImpl(
            repository = repository
        )

    @Provides
    @ViewModelScoped
    fun provideAddTransactionUseCases(
        repository: TransactionRepository
    ): AddTransactionUseCases =
        AddTransactionUseCasesImpl(
            repository = repository
        )
}