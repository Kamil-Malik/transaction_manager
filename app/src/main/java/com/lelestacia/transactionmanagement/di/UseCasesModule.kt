package com.lelestacia.transactionmanagement.di

import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import com.lelestacia.transactionmanagement.domain.usecases.DashboardUseCases
import com.lelestacia.transactionmanagement.domain.usecases.DashboardUseCasesImpl
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
}