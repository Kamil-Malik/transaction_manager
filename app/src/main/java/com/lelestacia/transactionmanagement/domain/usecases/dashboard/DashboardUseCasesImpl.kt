package com.lelestacia.transactionmanagement.domain.usecases.dashboard

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardUseCasesImpl @Inject constructor(
    private val repository: TransactionRepository
) : DashboardUseCases {

    override fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>> {
        return repository.getListOfTransaction()
    }
}