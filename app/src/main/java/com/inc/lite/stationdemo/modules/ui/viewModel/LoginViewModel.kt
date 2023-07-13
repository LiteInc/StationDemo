package com.inc.lite.stationdemo.modules.ui.viewModel

import androidx.lifecycle.ViewModel
import com.inc.lite.stationdemo.modules.ui.models.LoginUiState
import com.inc.lite.stationdemo.modules.ui.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val uiState = MutableStateFlow(LoginUiState())
    val _uiState = uiState.asStateFlow()

    val countriesList = listOf(
        Pair("Datong", "350"),
        Pair("Shuozhou", "351"),
        Pair("Xinzhou", "352"),
        Pair("Datong", "353"),
        Pair("Datong", "354"),
        Pair("Datong", "355"),
    )

    init {

    }
}