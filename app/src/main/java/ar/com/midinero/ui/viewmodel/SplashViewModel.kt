package ar.com.midinero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ar.com.midinero.core.Result
import ar.com.midinero.domain.LoadCategoriesUseCase
import ar.com.midinero.domain.ValidateUserAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val validateUserAuthUseCase: ValidateUserAuthUseCase,
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) :
    ViewModel() {

    fun init(): LiveData<Result<Pair<Boolean, Boolean>>> = liveData(Dispatchers.Main) {
        kotlin.runCatching {
            delay(3000)
            Pair(loadCategoriesUseCase.loadCategories(), validateUserAuthUseCase.validateUserAuth())
        }.onSuccess { result ->
            emit(Result.Success(result))
        }.onFailure { throwable ->
            emit(Result.Failure(Exception(throwable)))
        }
    }
}