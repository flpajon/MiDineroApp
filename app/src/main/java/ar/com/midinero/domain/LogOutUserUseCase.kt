package ar.com.midinero.domain

import ar.com.midinero.data.repository.UserRepository
import javax.inject.Inject

class LogOutUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun logOut() = repository.logOut()
}