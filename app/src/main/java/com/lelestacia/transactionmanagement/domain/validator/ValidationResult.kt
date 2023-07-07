package com.lelestacia.transactionmanagement.domain.validator

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String?,
)
