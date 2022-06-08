package com.example.chesstv.screens.profile.settings

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentProfileDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class FragmentProfileData: Fragment(R.layout.fragment_profile_data) {

    private val GALLERY_REQUEST_CODE = 100
    private lateinit var binding: FragmentProfileDataBinding
    lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileDataBinding.inflate(inflater, container, false)

        loadUserData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivUserAvatar.setOnClickListener {
            launchGallery()
        }

        binding.btnSave.setOnClickListener {
            uploadImage()
        }
    }

    private fun loadUserData() {
        val user = Firebase.auth.currentUser

        user?.let {
            // Name, email address, and profile photo Url

            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

        //    binding.etName.hint = name

            if (photoUrl != null) {
                activity?.let {
                    Glide.with(it)
                        .load(photoUrl)
                        .circleCrop()
                        .into(binding.ivUserAvatar)
                }
            }

        }
    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(activity)

        progressDialog.apply {
            setMessage("Загрузка данных")
            setCancelable(false)
            show()
        }

        val formatter = SimpleDateFormat("yyyy-MM-dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")


        storageReference.putFile(imageUri)
            .addOnSuccessListener {
                //binding.ivUserAvatar.setImageURI(null)

                activity?.let {
                    Glide.with(it)
                        .load(imageUri)
                        .circleCrop()
                        .into(binding.ivUserAvatar)
                }

                Toast.makeText(activity, "Данные загружены", Toast.LENGTH_SHORT).show()


                // Заугрузка аватарки и имени
                val user = FirebaseAuth.getInstance().currentUser

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setPhotoUri(imageUri)
                    .setDisplayName(
                        binding.etName.text.toString() + ' ' + binding.etSurname.text.toString()
                    ).build()
                user?.updateProfile(profileUpdates)

                if (progressDialog.isShowing) progressDialog.dismiss()
            }
            .addOnFailureListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(activity, "Ошибка", Toast.LENGTH_SHORT).show()
            }

    }

    private fun launchGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data?.data!!
            //binding.ivUserAvatar.setImageURI(imageUri)
            activity?.let {
                Glide.with(it)
                    .load(imageUri)
                    .circleCrop()
                    .into(binding.ivUserAvatar)
            }
        }
    }
}