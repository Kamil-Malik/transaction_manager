package com.lelestacia.transactionmanagement.data.mapper

import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.domain.model.TransactionModel

fun TransactionFirebaseModel.asTransactionModel() =
    TransactionModel(
        transactionID = transactionID,
        transactionDate = transactionDate,
        buyerName = buyerName,
        additionalInformation = additionalInformation,
        contactWhatsappNumber = contactWhatsappNumber,
        contactPhoneNumber = contactPhoneNumber,
    )