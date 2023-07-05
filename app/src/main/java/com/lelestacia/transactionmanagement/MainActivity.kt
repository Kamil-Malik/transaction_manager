package com.lelestacia.transactionmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lelestacia.transactionmanagement.ui.route.Screen
import com.lelestacia.transactionmanagement.ui.screen.dashboard.DashboardScreen
import com.lelestacia.transactionmanagement.ui.screen.dashboard.DashboardViewModel
import com.lelestacia.transactionmanagement.ui.theme.TransactionManagementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransactionManagementTheme {
                val navController: NavHostController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) {
                        val vm = hiltViewModel<DashboardViewModel>()
                        val transactions by vm.transactions.collectAsState()
                        DashboardScreen(
                            transactions = transactions,
                            onTransactionClicked = {}
                        )
                    }
                }
            }
        }
    }
}