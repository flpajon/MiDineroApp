package ar.com.midinero.domain

import ar.com.midinero.data.repository.UserRepository
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(private val repository: UserRepository)  {
    suspend fun signUp(email:String, password: String) = repository.signUp(email, password)
}