package com.ahmedmadhoun.unitoneassignment.ui.home_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.ahmedmadhoun.unitoneassignment.R
import com.ahmedmadhoun.unitoneassignment.adapters.CitiesAdapter
import com.ahmedmadhoun.unitoneassignment.adapters.ViewPagerAdapter
import com.ahmedmadhoun.unitoneassignment.databinding.FragmentHomeBinding
import com.ahmedmadhoun.unitoneassignment.ui.sign_in.SignInFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.sign_up_view.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val citiesAdapter = CitiesAdapter()
        binding!!.citiesRecyclerView.adapter = citiesAdapter
        binding!!.citiesRecyclerView.setHasFixedSize(true)
        binding!!.citiesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding!!.bottomNavigationView.background = null
        binding!!.bottomNavigationView.menu.getItem(2).isEnabled = false

        if (false) {
            binding!!.signUpView.root.visibility = View.GONE
            binding!!.adsView.root.visibility = View.VISIBLE
        } else {
            binding!!.signUpView.root.visibility = View.VISIBLE
            binding!!.adsView.root.visibility = View.GONE
        }
        binding!!.signUpView.root.signUpBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity, SignInFragment(), "SignIn Fragement").commit()
        }
        lifecycleScope.launch {
            viewModel.getCitiesStateFlow.collectLatest {
                citiesAdapter.submitList(it)
            }
        }
        lifecycleScope.launch {
            viewModel.getSliderCitiesStateFlow.collectLatest {
                viewPagerAdapter = ViewPagerAdapter(requireContext(), it)
                binding!!.viewPager.adapter = viewPagerAdapter
            }
        }

    }

}