package com.lelestacia.transactionmanagement.ui.route

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("detail/{transaction}") {

    }
    object AddTransaction: Screen("add_transaction")
}
