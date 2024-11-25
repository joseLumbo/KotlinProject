package com.example.myapptodo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapptodo.R
import com.example.myapptodo.data.TaskViewModel
import com.example.myapptodo.data.Task

class AddFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.findViewById<Button>(R.id.add_btn).setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val titleTxt = view?.findViewById<EditText>(R.id.add_Title)?.text.toString()
        val  descriptionTxt = view?.findViewById<EditText>(R.id.add_Description)?.text.toString()

        if(inputCheck(titleTxt, descriptionTxt)){
            val task = Task(id, titleTxt, descriptionTxt)
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(),"Tarefa adicionada com sucesso!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Todos os campos sâo obrigatorios!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(titleTxt: String, descriptionTxt: String): Boolean{
        return !(titleTxt.isEmpty() || descriptionTxt.isEmpty())
   }
}
