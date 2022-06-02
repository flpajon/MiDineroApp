package ar.com.midinero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.midinero.core.Result
import ar.com.midinero.domain.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: SignUpUserUseCase) : ViewModel() {

    private val _isSignUp = MutableLiveData<Result<Boolean>>()

    fun isSignUp(): LiveData<Result<Boolean>> = _isSignUp

    fun signUp(email: String, password: String) = viewModelScope.launch {
        kotlin.runCatching {
            _isSignUp.value = Result.Loading()
            useCase.signUp(email, password)
        }.onSuccess { result ->
            _isSignUp.value = Result.Success(result)
        }.onFailure { throwable ->
            _isSignUp.value = Result.Failure(Exception(throwable))
        }
    }
}