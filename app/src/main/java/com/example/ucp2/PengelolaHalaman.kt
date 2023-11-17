package com.example.ucp2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.data.DataSource.dospem

enum class PengelolaHalaman {
    Home,
    Form,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengelolaHalaman(
    classViewModel: ClassViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { innerPadding ->
        val uiState by classViewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(PengelolaHalaman.Home.name){
                HalamanHome(
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Form.name)
                    })}
            composable(PengelolaHalaman.Form.name){
                val context = LocalContext.current
                HalamanForm(
                    dosenPilihan = dospem.map{ id -> context.resources.getString(id)},
                    onSelectionChanged = { classViewModel.setDp(it)},
                    onPilihChanged = { classViewModel.setDp1(it)},
                    onSubmitBUttonClicked = {
                        classViewModel.setContact(it)
                        navController.navigate(PengelolaHalaman.Detail.name)},
                )
            }
            composable(
                route = PengelolaHalaman.Detail.name){
                HalamanResult(orderUIState = uiState, onCancelButtonClicked = { cancelOrderAndNavigateToForm(navController) },
                )
            }
        }
    }
}

fun cancelOrderAndNavigateToForm(navController: NavHostController) {
    navController.popBackStack(PengelolaHalaman.Form.name, inclusive = false)
}