package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.model.Task
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

//        val tasks = App.db.taskDao().getAll()
//        adapter.addTasks(tasks)

//        setFragmentResultListener(TaskFragment.RESULT_TASK) { key, bundle ->
//            val result = bundle.getSerializable("task") as Task
//            adapter.addTask(result)
//        }

        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun setData(){
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    private fun onLongClick(task: Task){

        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Do you want to delete?")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })
        alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                setData()
            }
        })
        alertDialog.create().show()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}