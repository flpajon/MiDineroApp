package ar.com.midinero.data.datasource

interface UserDataSource {

    suspend fun authUser(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun validateUserAuth(): Boolean
    suspend fun logOut()
}