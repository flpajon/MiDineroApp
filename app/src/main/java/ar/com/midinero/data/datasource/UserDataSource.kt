package ar.com.midinero.data.datasource

interface UserDataSource {

    suspend fun authUser(email: String, password: String): Boolean
    suspend fun singUp(email: String, password: String): Boolean
}