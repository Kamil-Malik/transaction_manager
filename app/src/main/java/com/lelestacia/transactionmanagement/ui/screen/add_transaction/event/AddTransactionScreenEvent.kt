package com.lelestacia.transactionmanagement.ui.screen.add_transaction.event

sealed class AddTransactionScreenEvent

data class OnTransactionDateChanged(val newTransactionDate: Long) : AddTransactionScreenEvent()

data class OnBuyerNameChanged(val newBuyerName: String) : AddTransactionScreenEvent()

data class OnContactPhoneNumberChanged(val newContactPhoneNumber: String) :
    AddTransactionScreenEvent()

data class OnWhatsappContactPhoneNumberChanged(val newWhatsappContactPhoneNumber: String) :
    AddTransactionScreenEvent()

data class OnAdditionalInformationChanged(val newAdditionalInformation: String) :
    AddTransactionScreenEvent()

object OnDatePickerStateChanged : AddTransactionScreenEvent()

object OnUploadTransaction : AddTransactionScreenEvent()