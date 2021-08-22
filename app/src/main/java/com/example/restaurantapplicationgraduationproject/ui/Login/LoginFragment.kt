package com.example.restaurantapplicationgraduationproject.ui.Login

import android.animation.Animator
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.restaurantapplicationgraduationproject.databinding.FragmentLoginBinding
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var _binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnRegister.setOnClickListener {
            val action=LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        _binding.btnLogin.setOnClickListener {
            val email = _binding.editTextEmail.text.toString()
            val password = _binding.editTextPassword.text.toString()
            viewModel.login(email, password).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            viewGones()
                            _binding.loginAnimation.visibility = View.VISIBLE
                            _binding.loginAnimation.addAnimatorListener(object :
                                Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator?) {
                                    Log.v("Animation", "Started")
                                }
                                override fun onAnimationEnd(animation: Animator?) {

                                    val action=LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment()
                                    findNavController().navigate(action)
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                    Log.v("Animation", "Canceled")
                                }

                                override fun onAnimationRepeat(animation: Animator?) {
                                    Log.v("Animation", "Repeated")
                                }
                            })
                        }
                        Resource.Status.ERROR -> {

                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("ok") { dialog, button ->
                                    dialog.dismiss()
                                }
                            dialog.show()
                        } }}) }


    }

    private fun viewGones(){
        _binding.editTextEmail.visibility = View.GONE
        _binding.textLogin.visibility=View.GONE
        _binding.textViewEnterAccount.visibility=View.GONE
        _binding.editTextPassword.visibility = View.GONE
        _binding.textViewDontAccount.visibility=View.GONE
        _binding.btnRegister.visibility=View.GONE
        _binding.btnLogin.visibility = View.GONE
    }
}