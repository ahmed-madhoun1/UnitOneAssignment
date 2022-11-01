package com.ahmedmadhoun.unitoneassignment.ui.verfiy_code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedmadhoun.unitoneassignment.R
import com.ahmedmadhoun.unitoneassignment.databinding.FragmentVerifyCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerfiyCodeFragment : Fragment(R.layout.fragment_verify_code) {

    lateinit var binding: FragmentVerifyCodeBinding
    private val viewModel: VerfiyCodeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVerifyCodeBinding.bind(view)

        binding.sendVerificationCodeBtn.setOnClickListener {
            viewModel.verifyCode(
                requireActivity(),
                binding.verificationCodeEditText.text.toString()
            )
            findNavController().navigate(VerfiyCodeFragmentDirections.actionVerfiyCodeFragmentToHomeFragment())
        }

    }


}