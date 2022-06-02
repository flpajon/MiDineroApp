package ar.com.midinero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import ar.com.midinero.core.Result
import ar.com.midinero.domain.LogOutUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: LogOutUserUseCase) : ViewModel() {

    fun logOut(): LiveData<Result<Boolean>> =
        liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            kotlin.runCatching {
                emit(Result.Loading())
                useCase.logOut()
            }.onSuccess {
                emit(Result.Success(true))
            }.onFailure { throwable ->
                emit(Result.Failure(Exception(throwable)))
            }
        }
}