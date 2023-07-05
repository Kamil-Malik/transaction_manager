package com.lelestacia.transactionmanagement.data.repository

import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSource
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : TransactionRepository {

    override fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>> {
        return dataSource.getListOfTransaction()
    }
}