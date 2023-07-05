package com.lelestacia.transactionmanagement.data.datasource

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirestoreDataSourceImpl(
    private val service: FirebaseFirestore
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
        }
    }

    private companion object {
        private const val TRANSACTION = "transaction"
    }
}