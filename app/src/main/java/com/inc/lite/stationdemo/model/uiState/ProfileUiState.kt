package com.inc.lite.stationdemo.model.uiState

import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.model.User

data class ProfileUiState(
    val user: User = User(),
    val statusBarUiState: StatusBarUiState = StatusBarUiState(),
)