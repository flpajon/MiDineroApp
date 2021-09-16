package ar.com.midinero.data.datasource

import android.util.Log
import ar.com.midinero.MiDineroApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserDataSourceImpl @Inject constructor() : UserDataSource {
    override suspend fun singUp(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val auth = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            auth.let {
                true
            }
            false
        }

    override suspend fun authUser(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            val auth = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            auth.user?.let {
                true
            }
            false
        }
}