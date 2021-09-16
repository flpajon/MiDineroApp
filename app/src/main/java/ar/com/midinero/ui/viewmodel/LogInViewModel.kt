package ar.com.midinero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.midinero.core.Result
import ar.com.midinero.domain.AuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val useCase: AuthUserUseCase) : ViewModel() {

    private val _isUserAuth = MutableLiveData<Result<Boolean>>(Result.Loading())

    fun isUserAuth(): LiveData<Result<Boolean>> = _isUserAuth

    fun logIn(email: String, password: String) = viewModelScope.launch {
        kotlin.runCatching {
            useCase.authUser(email, password)
        }.onSuccess { result ->
            _isUserAuth.value = Result.Success(result)
        }.onFailure { throwable ->
            _isUserAuth.value = Result.Failure(Exception(throwable))
        }
    }
}