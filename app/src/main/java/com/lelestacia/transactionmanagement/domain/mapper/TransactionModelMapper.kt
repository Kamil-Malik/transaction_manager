package com.lelestacia.transactionmanagement.domain.mapper

import com.google.firebase.Timestamp
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.domain.model.TransactionModel

fun TransactionModel.asFirebaseModel() =
    TransactionFirebaseModel(
        transactionID = transactionID,
        transactionDate = transactionDate,
        buyerName = buyerName,
        additionalInformation = additionalInformation,
        contactWhatsappNumber = contactWhatsappNumber,
        contactPhoneNumber = contactPhoneNumber,
        createdAt = Timestamp.now()
    )