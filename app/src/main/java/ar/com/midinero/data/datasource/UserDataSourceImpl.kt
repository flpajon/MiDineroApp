package ar.com.midinero.data.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserDataSourceImpl @Inject constructor(private val firebaseAuthService: FirebaseAuth) :
    UserDataSource {
    override suspend fun signUp(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val auth = firebaseAuthService.createUserWithEmailAndPassword(email, password).await()
            !auth.user?.uid.isNullOrEmpty()
        }

    override suspend fun validateUserAuth(): Boolean = withContext(Dispatchers.IO) {
        val auth = firebaseAuthService.currentUser?.uid
        !auth.isNullOrEmpty()
    }

    override suspend fun authUser(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val auth = firebaseAuthService.signInWithEmailAndPassword(email, password).await()
            !auth.user?.uid.isNullOrEmpty()
        }

    override suspend fun logOut() = withContext(Dispatchers.IO) {
        firebaseAuthService.signOut()
    }
}