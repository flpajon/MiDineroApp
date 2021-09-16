package ar.com.midinero.domain

import ar.com.midinero.data.repository.UserRepository
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun authUser(email: String, password: String): Boolean =
        repository.authUser(email, password)
}