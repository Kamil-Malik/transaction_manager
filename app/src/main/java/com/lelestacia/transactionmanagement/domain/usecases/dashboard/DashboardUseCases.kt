package com.lelestacia.transactionmanagement.domain.usecases.dashboard

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.flow.Flow

interface DashboardUseCases {
    fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>>
}