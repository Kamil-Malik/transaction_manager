package com.lelestacia.transactionmanagement.domain.usecases.add_transaction

import com.lelestacia.transactionmanagement.data.repository.TransactionRepository
import com.lelestacia.transactionmanagement.domain.mapper.asFirebaseModel
import com.lelestacia.transactionmanagement.domain.model.TransactionModel
import com.lelestacia.transactionmanagement.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTransactionUseCasesImpl @Inject constructor(
    private val repository: TransactionRepository
) : AddTransactionUseCases {

    override fun uploadTransaction(transaction: TransactionModel): Flow<Resource<String>> {
        return repository.uploadTransaction(transaction.asFirebaseModel())
    }
}