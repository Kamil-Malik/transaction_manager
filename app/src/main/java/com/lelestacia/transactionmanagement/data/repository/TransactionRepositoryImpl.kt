package com.lelestacia.transactionmanagement.data.repository

import android.content.Context
import com.lelestacia.transactionmanagement.R
import com.lelestacia.transactionmanagement.data.datasource.FirestoreDataSource
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource,
    private val context: Context
) : TransactionRepository {

    override fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>> {
        return dataSource.getListOfTransaction()
    }

    override fun uploadTransaction(transaction: TransactionFirebaseModel): Flow<Resource<String>> {
        return flow<Resource<String>> {
            dataSource.uploadTransaction(transaction)
            emit(Resource.Success(data = context.getString(R.string.success_message_transaction_uploaded)))
        }.onStart {
            emit(Resource.Loading)
        }.catch {
            emit(
                Resource.Error(
                    data = null,
                    message = it.message
                )
            )
        }
    }
}