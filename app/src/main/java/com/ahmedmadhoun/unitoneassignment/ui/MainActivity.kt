package com.ahmedmadhoun.unitoneassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedmadhoun.unitoneassignment.R
import com.ahmedmadhoun.unitoneassignment.databinding.ActivityMainBinding
import com.ahmedmadhoun.unitoneassignment.ui.home_screen.HomeFragment
import com.ahmedmadhoun.unitoneassignment.ui.sign_in.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity, HomeFragment(), "Home Fragement").commit()

    }
}