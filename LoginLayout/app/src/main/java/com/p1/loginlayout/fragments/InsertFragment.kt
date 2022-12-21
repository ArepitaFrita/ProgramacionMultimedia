package com.p1.loginlayout.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.R
import com.p1.loginlayout.databinding.FragmentEditBinding
import com.p1.loginlayout.model.Champion
import com.p1.loginlayout.model.ChampionViewModel

// EditFragment is the fragment that allows the user to edit the details of a champion
class InsertFragment(db: ChampionsDBHelper) : Fragment() {
    val dbHelper = db

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val championsViewModel: ChampionViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        insertChamp()
        activity?.title = getString(R.string.insert_fragment)
        return binding.root
    }

    private fun insertChamp() {
        val btn = binding.submitChampButton
        val builder = AlertDialog.Builder(context)
        btn.setOnClickListener {
            dbHelper.insertChamp(
                Champion(
                    name=binding.champName.text.toString(),
                    dmg=binding.champDmg.text.toString(),
                    release = binding.champRelease.text.toString(),
                    splash= R.drawable.aatrox,
                    role = binding.champRole.text.toString()
                )
            )
            builder.setTitle("Champion Added")
            builder.setMessage("${binding.champName.text} has been added to the database")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
            binding.apply {
                champName.text?.clear()
                champDmg.text?.clear()
                champRelease.text?.clear()
                champRole.text?.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}