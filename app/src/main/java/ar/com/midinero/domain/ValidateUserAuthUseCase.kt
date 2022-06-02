package ar.com.midinero.domain

import ar.com.midinero.data.repository.UserRepository
import javax.inject.Inject

class ValidateUserAuthUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun validateUserAuth(): Boolean = repository.validateUserAuth()
}