package com.lelestacia.transactionmanagement.domain.usecases.add_transaction

import com.lelestacia.transactionmanagement.domain.model.TransactionModel
import com.lelestacia.transactionmanagement.util.Resource
import kotlinx.coroutines.flow.Flow

interface AddTransactionUseCases {
    fun uploadTransaction(transaction: TransactionModel): Flow<Resource<String>>
}