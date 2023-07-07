package com.lelestacia.transactionmanagement.ui.screen.add_transaction.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.transactionmanagement.domain.usecases.add_transaction.AddTransactionUseCases
import com.lelestacia.transactionmanagement.domain.validator.BuyerNameValidator
import com.lelestacia.transactionmanagement.domain.validator.DateValidator
import com.lelestacia.transactionmanagement.domain.validator.PhoneNumberValidator
import com.lelestacia.transactionmanagement.domain.validator.ValidationResult
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.AddTransactionScreenEvent
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnAdditionalInformationChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnBuyerNameChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnContactPhoneNumberChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnDatePickerStateChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnTransactionDateChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnUploadTransaction
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnWhatsappContactPhoneNumberChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.state.AddTransactionScreenState
import com.lelestacia.transactionmanagement.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val useCases: AddTransactionUseCases,
    private val dateValidator: DateValidator,
    private val buyerNameValidator: BuyerNameValidator,
    private val phoneNumberValidator: PhoneNumberValidator
) : ViewModel() {

    private val _addTransactionScreenState: MutableStateFlow<AddTransactionScreenState> =
        MutableStateFlow(AddTransactionScreenState())
    val addTransactionScreenState: StateFlow<AddTransactionScreenState> =
        _addTransactionScreenState.asStateFlow()

    private val _uploadTransactionResult: MutableStateFlow<Resource<String>> =
        MutableStateFlow(Resource.None)
    val uploadTransactionResult: StateFlow<Resource<String>> =
        _uploadTransactionResult.asStateFlow()

    fun onEvent(event: AddTransactionScreenEvent) {
        when (event) {
            OnDatePickerStateChanged -> _addTransactionScreenState.update {
                it.copy(isDatePickerOpened = !it.isDatePickerOpened)
            }

            is OnTransactionDateChanged -> _addTransactionScreenState.update {
                it.copy(
                    transactionDateError = null,
                    transaction = it.transaction.copy(
                        transactionDate = event.newTransactionDate
                    )
                )
            }

            is OnBuyerNameChanged -> _addTransactionScreenState.update {
                it.copy(
                    buyerNameError = null,
                    transaction = it.transaction.copy(
                        buyerName = event.newBuyerName
                    )
                )
            }

            is OnContactPhoneNumberChanged -> _addTransactionScreenState.update {
                it.copy(
                    contactPhoneNumberError = null,
                    transaction = it.transaction.copy(
                        contactPhoneNumber = event.newContactPhoneNumber
                    )
                )
            }

            is OnWhatsappContactPhoneNumberChanged -> _addTransactionScreenState.update {
                it.copy(
                    contactWhatsappPhoneNumberError = null,
                    transaction = it.transaction.copy(
                        contactWhatsappNumber =
                        event.newWhatsappContactPhoneNumber.ifBlank {
                            null
                        }
                    )
                )
            }

            is OnAdditionalInformationChanged -> _addTransactionScreenState.update {
                it.copy(
                    transaction = it.transaction.copy(
                        additionalInformation = event.newAdditionalInformation
                    )
                )
            }

            OnUploadTransaction -> {
                uploadTransaction()
            }
        }
    }

    private fun uploadTransaction() = viewModelScope.launch {
        val transactionModel = addTransactionScreenState.value.transaction

        val validationsResult = mutableListOf<ValidationResult>(
            dateValidator(transactionModel.transactionDate),
            buyerNameValidator(buyerName = transactionModel.buyerName),
            phoneNumberValidator(phoneNumber = transactionModel.contactPhoneNumber),
        )

        if (transactionModel.contactWhatsappNumber != null) {
            validationsResult.add(phoneNumberValidator(phoneNumber = transactionModel.contactWhatsappNumber))
        }

        if (validationsResult.any { it.errorMessage != null }) {
            _addTransactionScreenState.update {
                it.copy(
                    transactionDateError = validationsResult[0].errorMessage,
                    buyerNameError = validationsResult[1].errorMessage,
                    contactPhoneNumberError = validationsResult[2].errorMessage,
                    contactWhatsappPhoneNumberError =
                    if (transactionModel.contactWhatsappNumber != null) {
                        validationsResult[3].errorMessage
                    } else {
                        null
                    }
                )
            }
            return@launch
        }

        useCases.uploadTransaction(transactionModel).collectLatest { uploadTransactionResult ->
            _uploadTransactionResult.update { uploadTransactionResult }
        }
    }
}