package com.ahmedmadhoun.unitoneassignment.ui.verfiy_code

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class VerfiyCodeViewModel @Inject constructor(
    val repository: CitiesRepository,
) : ViewModel() {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var _verificationId: String

    fun verifyCode(activity: Activity, code: String) {
        if (::_verificationId.isInitialized) {
            val credential = PhoneAuthProvider.getCredential(_verificationId, code)
            signInWithPhoneAuthCredential(activity, credential)
        }
    }

    private fun signInWithPhoneAuthCredential(activity: Activity, credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = task.result?.user

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }


}