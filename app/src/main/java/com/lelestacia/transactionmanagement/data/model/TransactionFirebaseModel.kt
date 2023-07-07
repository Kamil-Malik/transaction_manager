package com.lelestacia.transactionmanagement.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class TransactionFirebaseModel(

    @get:PropertyName(TRANSACTION_ID)
    @set:PropertyName(TRANSACTION_ID)
    var transactionID: String = "",

    @get:PropertyName(TRANSACTION_DATE)
    @set:PropertyName(TRANSACTION_DATE)
    var transactionDate: Long = 0,

    @get:PropertyName(BUYER_NAME)
    @set:PropertyName(BUYER_NAME)
    var buyerName: String = "",

    @get:PropertyName(ADDITIONAL_INFORMATION)
    @set:PropertyName(ADDITIONAL_INFORMATION)
    var additionalInformation: String? = null,

    @get:PropertyName(CONTACT_WHATSAPP_NUMBER)
    @set:PropertyName(CONTACT_WHATSAPP_NUMBER)
    var contactWhatsappNumber: String? = null,

    @get:PropertyName(CONTACT_PHONE_NUMBER)
    @set:PropertyName(CONTACT_PHONE_NUMBER)
    var contactPhoneNumber: String = "",

    @get:PropertyName("created_at")
    @set:PropertyName("created_at")
    var createdAt: Timestamp = Timestamp.now(),
) {

    companion object {
        private const val TRANSACTION_ID = "transaction_id"
        private const val TRANSACTION_DATE = "transaction_date"
        private const val BUYER_NAME = "buyer_name"
        private const val CONTACT_WHATSAPP_NUMBER = "contact_whatsapp_number"
        private const val CONTACT_PHONE_NUMBER = "contact_phone_number"
        private const val ADDITIONAL_INFORMATION = "additional_information"
    }
}