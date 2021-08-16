package com.example.restaurantapplicationgraduationproject.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.restaurantapplicationgraduationproject.R
import com.example.restaurantapplicationgraduationproject.databinding.FragmentOnboardingBinding
import com.example.restaurantapplicationgraduationproject.ui.onBoarding.utils.OnBoardingAdapter
import com.example.restaurantapplicationgraduationproject.ui.onBoarding.utils.ZoomOutPageTransformer

class OnBoardingFragment: Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = OnBoardingAdapter(requireActivity())
        binding.onboardingViewPager.adapter = adapter
        binding.onboardingViewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        binding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                } else if (position == 2) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.finish)
                    binding.nextButton.setOnClickListener {
                         //SharedPrefManager(requireContext()).setOnboardingSeen()
                        //findNavController().navigate(R.id.action_onboardingFragment_to_loginAndSignupFragment)

                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.next)
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem - 1
                    }
                }
            }
        })

    }

}