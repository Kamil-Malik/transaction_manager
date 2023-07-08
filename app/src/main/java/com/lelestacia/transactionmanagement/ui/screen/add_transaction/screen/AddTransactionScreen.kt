package com.lelestacia.transactionmanagement.ui.screen.add_transaction.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.transactionmanagement.R
import com.lelestacia.transactionmanagement.domain.model.TransactionModel
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.AddTransactionScreenEvent
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnAdditionalInformationChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnBuyerNameChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnContactPhoneNumberChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnDatePickerStateChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnTransactionDateChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnUploadTransaction
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.event.OnWhatsappContactPhoneNumberChanged
import com.lelestacia.transactionmanagement.ui.screen.add_transaction.state.AddTransactionScreenState
import com.lelestacia.transactionmanagement.ui.theme.TransactionManagementTheme
import com.lelestacia.transactionmanagement.util.Resource
import com.parassidhu.simpledate.toDateStandard
import kotlinx.coroutines.delay
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    state: AddTransactionScreenState,
    onEvent: (AddTransactionScreenEvent) -> Unit,
    onBack: () -> Unit,
    uploadTransactionResult: Resource<String>
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Add new transaction") })
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            }
        ) { paddingValues ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {

                val datePickerState = rememberDatePickerState()
                val onDatePickerStateChanged: () -> Unit = {
                    onEvent(OnDatePickerStateChanged)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val transactionDate: String =
                        if (state.transaction.transactionDate == 0.toLong()) {
                            "Date haven't been selected"
                        } else {
                            Date(state.transaction.transactionDate).toDateStandard()
                        }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = transactionDate,
                            onValueChange = {},
                            enabled = false,
                            isError = state.transactionDateError != null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        AnimatedVisibility(visible = state.transactionDateError != null) {
                            Text(
                                text = state.transactionDateError
                                    ?: stringResource(id = R.string.error_message_unknown),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    IconButton(
                        onClick = onDatePickerStateChanged
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = "Open Calendar"
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.transaction.buyerName,
                        onValueChange = { newBuyerName ->
                            onEvent(OnBuyerNameChanged(newBuyerName))
                        },
                        label = { Text(text = "Buyer Name") },
                        placeholder = {
                            Text(
                                text = "Insert Buyer name here",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            autoCorrect = false,
                            imeAction = ImeAction.Next
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLeadingIconColor = MaterialTheme.colorScheme.primary
                        ),
                        isError = state.buyerNameError != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    AnimatedVisibility(visible = state.buyerNameError != null) {
                        Text(
                            text = state.buyerNameError
                                ?: stringResource(id = R.string.error_message_unknown),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.transaction.contactPhoneNumber,
                        onValueChange = { newPhoneNumber ->
                            onEvent(OnContactPhoneNumberChanged(newPhoneNumber))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = "Contact Phone Number") },
                        placeholder = {
                            Text(
                                text = "Insert Contact Phone Number here",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(
                                    start = 4.dp
                                )
                            )
                        },
                        prefix = { Text(text = "+62") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLeadingIconColor = MaterialTheme.colorScheme.primary
                        ),
                        isError = state.contactPhoneNumberError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    AnimatedVisibility(visible = state.contactPhoneNumberError != null) {
                        Text(
                            text = state.contactPhoneNumberError
                                ?: stringResource(id = R.string.error_message_unknown),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                val contactWhatsappPhoneNumber: String =
                    if (state.transaction.contactWhatsappNumber.isNullOrBlank()) {
                        ""
                    } else {
                        state.transaction.contactWhatsappNumber
                    }

                Column {
                    OutlinedTextField(
                        value = contactWhatsappPhoneNumber,
                        onValueChange = { newWhatsappPhoneNumber ->
                            onEvent(OnWhatsappContactPhoneNumberChanged(newWhatsappPhoneNumber))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = "Whatsapp Phone Number") },
                        placeholder = {
                            Text(
                                text = "Optional",
                                modifier = Modifier.padding(
                                    start = 4.dp
                                )
                            )
                        },
                        prefix = { Text(text = "+62") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLeadingIconColor = MaterialTheme.colorScheme.primary
                        ),
                        isError = state.contactWhatsappPhoneNumberError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    AnimatedVisibility(visible = state.contactWhatsappPhoneNumberError != null) {
                        Text(
                            text = state.contactWhatsappPhoneNumberError
                                ?: stringResource(id = R.string.error_message_unknown),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                OutlinedTextField(
                    value = state.transaction.additionalInformation ?: "",
                    onValueChange = { newAdditionalInformation ->
                        onEvent(OnAdditionalInformationChanged(newAdditionalInformation))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Notes,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Additional Information") },
                    placeholder = { Text(text = "Optional") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLeadingIconColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onBack,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = { onEvent(OnUploadTransaction) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Upload Transaction")
                    }
                }

                AnimatedVisibility(visible = uploadTransactionResult !is Resource.None) {
                    when (uploadTransactionResult) {
                        is Resource.Error -> Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                space = 4.dp,
                                alignment = Alignment.CenterVertically
                            ),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = uploadTransactionResult.message
                                    ?: stringResource(id = R.string.error_message_unknown)
                            )
                            Button(
                                onClick = { onEvent(OnUploadTransaction) },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Retry")
                            }
                        }

                        Resource.Loading -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        Resource.None -> Unit
                        is Resource.Success -> {
                            LaunchedEffect(key1 = Unit) {
                                snackBarHostState.showSnackbar(
                                    message = uploadTransactionResult.data as String,
                                    duration = SnackbarDuration.Short
                                )
                                delay(2000)
                                onBack()
                            }
                        }
                    }
                }

                AnimatedVisibility(visible = state.isDatePickerOpened) {
                    DatePickerDialog(
                        onDismissRequest = onDatePickerStateChanged,
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                                        onEvent(OnTransactionDateChanged(selectedDateMillis))
                                        onDatePickerStateChanged()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            ) {
                                Text(text = "Select Date")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    onEvent(OnDatePickerStateChanged)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                ),
                            ) {
                                Text(text = "Cancel")
                            }
                        },
                        content = {
                            DatePicker(state = datePickerState)
                        })
                }
            }
        }
    }
}

@Preview(
    name = "Day Mode",
    group = "Normal State",
)
@Preview(
    name = "Dark Mode",
    group = "Normal State",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewAddTransactionScreenNormalState() {
    TransactionManagementTheme {
        AddTransactionScreen(
            state = AddTransactionScreenState(),
            onEvent = {},
            onBack = {},
            uploadTransactionResult = Resource.None
        )
    }
}

@Preview(
    name = "Day Mode",
    group = "Error Form State",
)
@Preview(
    name = "Dark Mode",
    group = "Error Form State",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewAddTransactionScreenErrorFormValidation() {
    TransactionManagementTheme {
        AddTransactionScreen(
            state = AddTransactionScreenState(
                transaction = TransactionModel(
                    transactionID = "",
                    transactionDate = 0,
                    buyerName = "",
                    additionalInformation = null,
                    contactPhoneNumber = "082259997760",
                    contactWhatsappNumber = null
                ),
                buyerNameError = stringResource(id = R.string.error_message_buyer_name_is_empty),
                contactPhoneNumberError = stringResource(id = R.string.error_message_phone_number_is_empty)
            ),
            onEvent = {},
            onBack = {},
            uploadTransactionResult = Resource.None
        )
    }
}

@Preview(
    name = "Day Mode",
    group = "Error on API",
)
@Preview(
    name = "Dark Mode",
    group = "Error on API",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewAddTransactionScreenErrorOnAPI() {
    TransactionManagementTheme {
        AddTransactionScreen(
            state = AddTransactionScreenState(),
            onEvent = {},
            onBack = {},
            uploadTransactionResult = Resource.Error(
                data = null,
                message = stringResource(id = R.string.error_message_connection_problem)
            )
        )
    }
}