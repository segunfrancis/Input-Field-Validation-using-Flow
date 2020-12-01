package com.project.segunfrancis.inputfieldvalidationusingflow

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    val isLoginEnabled: Flow<Boolean> = combine(_email, _password) { email, password ->
        val isEmailCorrect = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordCorrect = password.length > 7
        return@combine isEmailCorrect and isPasswordCorrect
    }
}