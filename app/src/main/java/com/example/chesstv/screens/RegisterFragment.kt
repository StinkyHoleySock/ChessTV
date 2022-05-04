package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentLoginBinding
import com.example.chesstv.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment(R.layout.fragment_register) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            //findNavController().navigate(R.id.action_registerFragment_to_mainFragment2)
        }

        return binding.root
    }
}