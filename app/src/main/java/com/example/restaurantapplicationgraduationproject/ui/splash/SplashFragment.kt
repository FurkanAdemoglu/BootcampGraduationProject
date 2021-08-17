package com.example.restaurantapplicationgraduationproject.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.restaurantapplicationgraduationproject.R


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({

            val action=SplashFragmentDirections.actionSplashFragmentToSearchFragment()
            findNavController().navigate(action)

           // val action=SplashFragmentDirections.actionSplashFragmentToRestaurantListFragment()
            //findNavController().navigate(action)

            if(onBoardingFinished()){
               // val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                //findNavController().navigate(action)
            }
            else{
               // val action = SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
                //findNavController().navigate(action)
            }

        }, 5000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished():Boolean{
        val sharedPref=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }


}