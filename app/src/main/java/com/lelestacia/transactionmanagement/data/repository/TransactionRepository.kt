package com.lelestacia.transactionmanagement.data.repository

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>>
}