package com.lelestacia.transactionmanagement.ui.screen.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lelestacia.transactionmanagement.data.model.TransactionFirebaseModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    transactions: List<TransactionFirebaseModel>,
    onTransactionClicked: (TransactionFirebaseModel) -> Unit,
    onAddTransactionClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTransactionClicked) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new transaction")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            items(items = transactions, key = { it.transactionID }) { transaction ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTransactionClicked(transaction) }
                ) {
                    Text(text = "Transaction ID: ${transaction.transactionID}")
                    Text(text = "Transaction Date: ${transaction.transactionDate}")
                    Text(text = "Buyer: ${transaction.buyerName}")
                    Divider()
                }
            }
        }
    }
}