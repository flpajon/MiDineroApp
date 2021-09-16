package ar.com.midinero.domain

import ar.com.midinero.data.repository.UserRepository
import javax.inject.Inject

class SingUpUserUseCase @Inject constructor(private val repository: UserRepository)  {
    suspend fun singUp(email:String, password: String) = repository.singUp(email, password)
}