package com.example.restaurantapplicationgraduationproject.ui.completeProfile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.restaurantapplicationgraduationproject.R
import com.example.restaurantapplicationgraduationproject.databinding.FragmentCompleteBinding
import com.example.restaurantapplicationgraduationproject.model.entity.User
import com.example.restaurantapplicationgraduationproject.model.entity.profile.UserRequest
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CompleteFragment : Fragment() {
    private lateinit var _binding: FragmentCompleteBinding
    private val viewModel: CompleteViewModel by viewModels()
    private var image: Int = R.mipmap.no_data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompleteBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addListeners()
    }

    private fun initViews() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    setField(response.data?.user)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun setField(user: User?) {
        _binding.editTextName.setText(user?.name)
        _binding.editTextEmail.setText(user?.email)
        _binding.editTextAddress.setText(user?.address)
        _binding.editTextPhone.setText(user?.phone)

    }

    private fun addListeners() {
        _binding.btnUpdate.setOnClickListener { updateUser() }
    }


    private fun updateUser() {
        val name = _binding.editTextName.text.toString()
        val mail = _binding.editTextEmail.text.toString()
        val phone = _binding.editTextPhone.text.toString()
        val address = _binding.editTextAddress.text.toString()

        val user = UserRequest(mail, name, address, phone)
        viewModel.updateUser(user).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                val action=CompleteFragmentDirections.actionCompleteFragmentToProfileFragment()
                    findNavController().navigate(action)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun isSettingVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        if (isVisible.not()) {
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("There is a problem")
                .setPositiveButton("Cancel") { _, _ ->
                    findNavController().popBackStack()
                }.show()
        }
    }



}