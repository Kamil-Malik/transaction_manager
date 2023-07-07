package com.lelestacia.transactionmanagement.domain.validator

import android.content.Context
import com.lelestacia.transactionmanagement.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateValidator(
    private val context: Context
) {

    operator fun invoke(date: Long): ValidationResult {
        if (date == 0.toLong()) {
            return ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.error_message_date_is_zero)
            )
        }

        return try {
            val realDateObject = Date(date)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
            dateFormat.isLenient = false
            dateFormat.parse(dateFormat.format(date))
            ValidationResult(
                isValid = true,
                errorMessage = null
            )
        } catch (e: Exception) {
            ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.error_message_date_is_invalid)
            )
        }
    }
}