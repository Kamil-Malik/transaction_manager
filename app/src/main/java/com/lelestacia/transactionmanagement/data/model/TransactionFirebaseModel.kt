package com.lelestacia.transactionmanagement.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class TransactionFirebaseModel(

    @get:PropertyName("transaction_id")
    @set:PropertyName("transaction_id")
    var transactionID: String = "",

    @get:PropertyName("buyer_name")
    @set:PropertyName("buyer_name")
    var buyerName: String = "",

    @get:PropertyName("transaction_date")
    @set:PropertyName("transaction_date")
    var transactionDate: Long = 0,

    @get:PropertyName("created_at")
    @set:PropertyName("created_at")
    var createdAt: Timestamp = Timestamp.now(),
)