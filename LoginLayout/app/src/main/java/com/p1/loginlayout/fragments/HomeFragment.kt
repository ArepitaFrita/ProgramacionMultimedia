package com.p1.loginlayout.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.databinding.FragmentHomeBinding

// HomeFragment is the fragment that shows two buttons to add to list and delete champions database
class HomeFragment(dbHelper: ChampionsDBHelper) : Fragment() {
    private val db = dbHelper
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        listChamps()
        deleteAll()
        activity?.title = "Champions Database"
        return binding.root
    }

    private fun listChamps() {
        binding.buttonListDB.setOnClickListener {
            Log.v("listChamps", "${db.listChamps()}")
        }
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(context)
        val whenDeleted = AlertDialog.Builder(context)
        binding.buttonRemoveDB.setOnClickListener {
            builder.setTitle("Are you sure you want to delete all champions?")
            builder.setPositiveButton("Yes") { _, _ ->
                Log.v("deleteAll", "Se han eliminado los siguientes campeones: ${db.listChamps()}")
                db.deleteAll()
                whenDeleted.setTitle("All champions have been deleted")
                whenDeleted.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                whenDeleted.create().show()

            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}