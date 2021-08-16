package com.example.restaurantapplicationgraduationproject.ui.onBoarding.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restaurantapplicationgraduationproject.ui.onBoarding.FirstOnBoardingFragment
import com.example.restaurantapplicationgraduationproject.ui.onBoarding.SecondOnBoardingFragment
import com.example.restaurantapplicationgraduationproject.ui.onBoarding.ThirdOnBoardingFragment

class OnBoardingAdapter(activity:FragmentActivity):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> FirstOnBoardingFragment()
            1-> SecondOnBoardingFragment()
            else-> ThirdOnBoardingFragment()

        }
    }

}