package com.lelestacia.transactionmanagement.data.repository

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>>
    fun uploadTransaction(transaction: TransactionFirebaseModel): Flow<Resource<String>>
}