package ar.com.midinero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.midinero.core.Result
import ar.com.midinero.domain.SingUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(private val useCase: SingUpUserUseCase) : ViewModel() {

    private val _isSingUp = MutableLiveData<Result<Boolean>>(Result.Loading())

    fun isSingUp(): LiveData<Result<Boolean>> = _isSingUp

    fun singUp(email: String, password: String) = viewModelScope.launch {
        kotlin.runCatching {
            useCase.singUp(email, password)
        }.onSuccess { result ->
            _isSingUp.value = Result.Success(result)
        }.onFailure { throwable ->
            _isSingUp.value = Result.Failure(Exception(throwable))
        }
    }
}