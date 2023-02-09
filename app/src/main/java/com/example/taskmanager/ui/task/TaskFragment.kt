package com.example.taskmanager.ui.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.model.Task
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TaskFragment : Fragment() {

    companion object {
        const val RESULT_TASK = "result.task"
    }

    private lateinit var binding: FragmentTaskBinding
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            onSave()
//            App.db.taskDao().insert(
//                Task(
//                    title = binding.etTitle.text.toString(),
//                    desc = binding.etDesc.text.toString()
//                )
//            )

//            setFragmentResult(
//                RESULT_TASK,
//                bundleOf(
//                    "task" to Task(
//                        title = binding.etTitle.text.toString(),
//                        desc = binding.etDesc.text.toString()
//                    )
//                )
//            )
            findNavController().navigateUp()
        }
    }

    private fun onSave() {
        val task = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()
    }

    private fun putTask(task: Task) {
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            db.collection(it).add(task).addOnSuccessListener {
                Log.d("ololo", "onSave: success!")
            }.addOnFailureListener {
                Log.d("ololo", "onSave: " + it.message)
            }
        }
    }
}