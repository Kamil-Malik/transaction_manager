package com.lelestacia.transactionmanagement.ui.screen.add_transaction.state

import com.lelestacia.transactionmanagement.domain.model.TransactionModel

data class AddTransactionScreenState(
    val transaction: TransactionModel = TransactionModel(
        transactionID = "",
        transactionDate = 0,
        buyerName = "",
        additionalInformation = null,
        contactWhatsappNumber = null,
        contactPhoneNumber = ""
    ),
    val isDatePickerOpened: Boolean = false,
    val transactionDateError: String? = null,
    val buyerNameError: String? = null,
    val contactPhoneNumberError: String? = null,
    val contactWhatsappPhoneNumberError: String? = null,
)