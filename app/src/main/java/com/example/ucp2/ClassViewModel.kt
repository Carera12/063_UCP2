package com.example.ucp2

import androidx.lifecycle.ViewModel
import com.example.ucp2.ui.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClassViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUiState())
    val stateUI: StateFlow<OrderUiState> = _stateUI.asStateFlow()

    fun setContact(listContact: MutableList<String>) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = listContact[0],
                nim = listContact[1],
                konsentrasi = listContact[2],
                judulSkripsi = listContact[3]
            )
        }
    }
    fun setDp(pilih:String){
        _stateUI.update { currentState -> currentState.copy(dp1 = pilih)
        }
    }

    fun setDp1(pilih2:String){
        _stateUI.update { currentState -> currentState.copy(dp2 = pilih2)
        }
    }
}