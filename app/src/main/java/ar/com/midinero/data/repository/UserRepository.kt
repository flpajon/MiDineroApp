package ar.com.midinero.data.repository

import ar.com.midinero.data.datasource.UserDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val dataSource: UserDataSource) {

    suspend fun authUser(email: String, password: String): Boolean =
        dataSource.authUser(email, password)

    suspend fun singUp(email: String, password: String): Boolean =
        dataSource.singUp(email, password)
}