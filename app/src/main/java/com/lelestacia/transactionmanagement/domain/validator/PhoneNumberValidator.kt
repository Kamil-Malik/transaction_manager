package com.lelestacia.transactionmanagement.domain.validator

import android.content.Context
import com.lelestacia.transactionmanagement.R

class PhoneNumberValidator(
    private val context: Context
) {

    operator fun invoke(phoneNumber: String): ValidationResult {

        if (phoneNumber.isBlank()) {
            return ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.error_message_phone_number_is_empty)
            )
        }

        val pattern = "^[+]?[0-9]{10,13}$"
        if (!pattern.toRegex().matches(phoneNumber)) {
            return ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.error_message_invalid_phone_number_format)
            )
        }

        return ValidationResult(
            isValid = true,
            errorMessage = null
        )
    }
}