package com.lelestacia.transactionmanagement.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel
import com.lelestacia.transactionmanagement.domain.usecases.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases: DashboardUseCases
) : ViewModel() {

    private val _transactions = MutableStateFlow(emptyList<TransactionFirebaseModel>())
    val transactions = _transactions.asStateFlow()

    private fun getTransactions() = viewModelScope.launch {
        useCases.getListOfTransaction().collectLatest { result ->
            _transactions.update { result }
        }
    }

    init {
        getTransactions()
    }
}