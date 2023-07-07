package com.lelestacia.transactionmanagement.domain.model

data class TransactionModel(
    val transactionID: String,
    val transactionDate: Long,
    val buyerName: String,
    val additionalInformation: String?,
    val contactWhatsappNumber: String?,
    val contactPhoneNumber: String,
)
