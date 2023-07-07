package com.lelestacia.transactionmanagement.data.datasource

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.flow.Flow

interface FirestoreDataSource {
    fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>>
    suspend fun uploadTransaction(transaction: TransactionFirebaseModel)
}