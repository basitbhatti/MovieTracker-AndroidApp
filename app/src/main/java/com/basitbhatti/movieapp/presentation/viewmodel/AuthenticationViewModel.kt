package com.basitbhatti.movieapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basitbhatti.movieapp.domain.model.RequestTokenModel
import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel
import com.basitbhatti.movieapp.domain.model.SessionResponseModel
import com.basitbhatti.movieapp.domain.use_cases.GetRequestTokenUseCase
import com.basitbhatti.movieapp.domain.use_cases.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {

    private var _session = MutableStateFlow<SessionResponseModel?>(null)
    val session = _session.asStateFlow()

    private var _requestToken = MutableStateFlow<RequestTokenResponseModel?>(null)
    val requestToken = _requestToken.asStateFlow()

    fun getRequestToken() {
        viewModelScope.launch(Dispatchers.IO) {
            _requestToken.value = getRequestTokenUseCase.invoke()
            Log.d("TAGTOKEN", "HomeScreenViewModel r: ${requestToken.value}")
        }
    }

    fun getSession(){
        viewModelScope.launch(Dispatchers.IO) {
            _session.value = getSessionUseCase(RequestTokenModel(requestToken.value?.request_token))
        }
    }

}