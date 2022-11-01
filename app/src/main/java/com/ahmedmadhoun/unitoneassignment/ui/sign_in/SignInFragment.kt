package com.ahmedmadhoun.unitoneassignment.ui.sign_in

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ahmedmadhoun.unitoneassignment.R
import com.ahmedmadhoun.unitoneassignment.databinding.FragmentSignInBinding
import com.ahmedmadhoun.unitoneassignment.ui.verfiy_code.VerfiyCodeFragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafeBrowsingThreat
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetStatusCodes
import com.google.android.gms.tasks.Tasks
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        navController = findNavController()


        binding.sendVerficationCodeBtn.setOnClickListener {
            val phoneText = binding.editText.text.toString()
            viewModel.captcha(requireActivity(), phoneText)
            if (phoneText.isNotEmpty()) {
                viewModel.sendVerificationCode(requireActivity(), phoneText)
                navController.navigate(SignInFragmentDirections.actionSignInFragment2ToVerfiyCodeFragment())
            }
        }


//        auth.setLanguageCode("ps")

    }


}