package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val currentUser: FirebaseUser? = auth.currentUser
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                
                when {
                    etEmail.toString().isEmpty() -> {
                        Toast.makeText(activity, "$etEmail is empty!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    etPassword.toString().isEmpty() -> {
                        Toast.makeText(activity, "$etPassword is empty!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    
                    else -> firebaseSignIn()
                }
                
            }

        }
        
    }

    private fun firebaseSignIn() {
        auth.signInWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                // TODO: bundleOf
                //val user = auth.currentUser
                //updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(activity, "Authentication failed. ${task.exception}",
                    Toast.LENGTH_LONG).show()
                //updateUI(null)
            }
        }
    }
}