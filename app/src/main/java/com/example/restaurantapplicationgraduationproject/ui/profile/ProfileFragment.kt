package com.example.restaurantapplicationgraduationproject.ui.profile


import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.restaurantapplicationgraduationproject.databinding.FragmentProfileBinding
import com.example.restaurantapplicationgraduationproject.model.entity.User
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.progressBar.show()
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    setField(response.data?.user)
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        if(isLoading)
        {
            _binding.progressBar.show()
            _binding.cardView.gone()
            _binding.cardViewImage.gone()
            _binding.nameTextView.gone()
            _binding.emailTextView.gone()
            _binding.phoneNumberTextView.gone()
            _binding.addressTextView.gone()
            _binding.buttonLogout.gone()
        }
        else{
            _binding.progressBar.gone()
            _binding.cardView.show()
            _binding.cardViewImage.show()
            _binding.nameTextView.show()
            _binding.emailTextView.show()
            _binding.phoneNumberTextView.show()
            _binding.addressTextView.show()
            _binding.buttonLogout.show()

        }
    }

    private fun setField(user: User?) {
        _binding.nameTextView.text = user?.name
        _binding.emailTextView.text = user?.email
        _binding.phoneNumberTextView.text = user?.phone
        _binding.addressTextView.text = user?.address
    }

    private fun addListeners() {

        _binding.buttonLogout.setOnClickListener {
            viewModel.logOut()
           val action=ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        _binding.btnUpdate.setOnClickListener {
            val action= ProfileFragmentDirections.actionProfileFragmentToCompleteFragment()
            findNavController().navigate(action)
        }
    }



}