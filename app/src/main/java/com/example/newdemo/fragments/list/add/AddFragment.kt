package com.example.newdemo.fragments.list.add

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
import com.example.newdemo.R
import com.example.newdemo.data.User
import com.example.newdemo.data.UserViewModel


private lateinit var mUserViewModel: UserViewModel
class AddFragment : Fragment() {


    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<Button>(R.id.add_btn).setOnClickListener {
            insertDataToDatabase()
        }

        return view

    }

    private fun insertDataToDatabase() {
        val firstNameEt = view?.findViewById<EditText>(R.id.firstName)
        val lastNameEt = view?.findViewById<EditText>(R.id.lastName)
        val ageEt = view?.findViewById<EditText>(R.id.age)

        val firstName = firstNameEt?.text.toString().trim()
        val lastName = lastNameEt?.text.toString().trim()
        val age = ageEt?.text.toString().trim()

        if (inputCheck(firstName, lastName, age)){

        val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))

        mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}