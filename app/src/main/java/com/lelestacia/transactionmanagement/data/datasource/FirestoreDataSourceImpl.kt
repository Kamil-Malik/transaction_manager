package com.lelestacia.transactionmanagement.data.datasource

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FirestoreDataSourceImpl(
    private val service: FirebaseFirestore,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : FirestoreDataSource {

    override fun getListOfTransaction(): Flow<List<TransactionFirebaseModel>> {
        return callbackFlow {
            val snapshotListener = EventListener<QuerySnapshot> { value, error ->
                if (error != null) {
                    close(error)
                } else {
                    value?.let { snapshot ->
                        trySend(snapshot.toObjects(TransactionFirebaseModel::class.java))
                    }
                }
            }
            service.collection(TRANSACTION).addSnapshotListener(snapshotListener)
            awaitClose()
        }.flowOn(ioDispatcher)
    }

    override suspend fun uploadTransaction(transaction: TransactionFirebaseModel) {
        withContext(ioDispatcher) {
            val newDocs = service.collection(TRANSACTION).document().get().await()
            val modifiedTransactionModel = transaction.copy(
                transactionID = newDocs.id
            )
            service.collection(TRANSACTION).document(newDocs.id).set(modifiedTransactionModel).await()
        }
    }

    private companion object {
        private const val TRANSACTION = "transaction"
    }
}