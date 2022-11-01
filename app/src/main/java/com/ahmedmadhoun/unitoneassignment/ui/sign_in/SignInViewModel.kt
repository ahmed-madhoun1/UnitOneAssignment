package com.ahmedmadhoun.unitoneassignment.ui.sign_in

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafeBrowsingThreat
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetStatusCodes
import com.google.android.gms.tasks.Tasks
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private lateinit var _verificationId: String
    private lateinit var code: String
    private val SAFE_BROWSING_API_KEY = "AIzaSyBBkIrSxfXjxaOmjVmGattuLOMR0kCc8ls"
    private var url = "www.google.com"
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun captcha(activity: Activity, phoneText: String) {
        SafetyNet.getClient(activity).lookupUri(
            url,
            SAFE_BROWSING_API_KEY,
            SafeBrowsingThreat.TYPE_POTENTIALLY_HARMFUL_APPLICATION,
            SafeBrowsingThreat.TYPE_SOCIAL_ENGINEERING
        ).addOnSuccessListener(activity) { sbResponse ->
            if (sbResponse.detectedThreats.isEmpty()) {
                // No threats found.

                // Call sendVerificationCode Function
                sendVerificationCode(activity, phoneText)

            } else {
                Log.d("AM", "Error: Threats found")
                // Threats found!
            }
        }.addOnFailureListener(activity) { e: Exception ->
            if (e is ApiException) {
                if (e.statusCode == SafetyNetStatusCodes.SAFE_BROWSING_API_NOT_INITIALIZED) {
                    viewModelScope.launch {
                        Tasks.await(SafetyNet.getClient(activity).initSafeBrowsing())
                    }
                }
                Log.d("AM", "Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
            } else {
                // A different, unknown type of error occurred.
                Log.d("AM", "Error: ${e.message}")
            }
        }

    }

    fun sendVerificationCode(activity: Activity, phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+970$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
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
            _verificationId = verificationId
        }
    }


}