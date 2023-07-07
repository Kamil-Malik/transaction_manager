package com.lelestacia.transactionmanagement.domain.validator

import android.content.Context
import com.lelestacia.transactionmanagement.R

class BuyerNameValidator(
    private val context: Context
) {

    operator fun invoke(buyerName: String): ValidationResult {
        return if (buyerName.isBlank()) {
            ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.error_message_buyer_name_is_empty)
            )
        } else {
            ValidationResult(
                isValid = true,
                errorMessage = null
            )
        }
    }
}