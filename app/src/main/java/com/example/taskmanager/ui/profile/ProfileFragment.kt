package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val auth = FirebaseAuth.getInstance()

    private val launcher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.avatarAccount.loadImage(photoUri.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.etName.setText(pref.getName())
        binding.etAge.setText(pref.getAge())
        binding.avatarAccount.loadImage(pref.getImage())

        binding.btnSaveProfile.setOnClickListener {
            pref.saveName(binding.etName.text.toString())
            pref.saveAge(binding.etAge.text.toString())
        }

        binding.avatarAccount.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

        binding.btnLogOutProfile.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.onBoardingFragment)
            auth.currentUser == null
            findNavController().navigate(R.id.authFragment)
        }
    }
}