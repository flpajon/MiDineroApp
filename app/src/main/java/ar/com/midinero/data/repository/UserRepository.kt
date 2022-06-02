package ar.com.midinero.data.repository

import ar.com.midinero.data.datasource.UserDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val dataSource: UserDataSource) {

    suspend fun authUser(email: String, password: String): Boolean =
        dataSource.authUser(email, password)

    suspend fun signUp(email: String, password: String): Boolean =
        dataSource.signUp(email, password)

    suspend fun validateUserAuth(): Boolean = dataSource.validateUserAuth()

    suspend fun logOut() = dataSource.logOut()
}