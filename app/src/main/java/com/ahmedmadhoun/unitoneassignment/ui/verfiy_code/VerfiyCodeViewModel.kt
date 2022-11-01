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
    private lateinit var code: String

    fun sendVerificationCode(activity: Activity, phoneNumber: String) {
        var number = phoneNumber
//        if (phoneNumber.toCharArray()[0].equals("0")) {
//            number = phoneNumber.substring(1)
//        }
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+972567746416")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(activity: Activity, code: String) {
        if (::_verificationId.isInitialized) {
            val credential = PhoneAuthProvider.getCredential(_verificationId, code)
            signInWithPhoneAuthCredential(activity, credential)
        }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            code = credential.smsCode!!
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.e("TAG", "FirebaseAuthInvalidCredentialsException: ${e.message}")
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                Log.e("TAG", "FirebaseTooManyRequestsException: ${e.message}")
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.e("TAG", "onCodeSent: ${verificationId}")
            _verificationId = verificationId
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