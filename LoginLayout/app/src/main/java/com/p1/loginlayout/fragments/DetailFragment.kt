package com.p1.loginlayout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.p1.loginlayout.R
import com.p1.loginlayout.databinding.FragmentDetailBinding
import com.p1.loginlayout.model.Champion
import com.p1.loginlayout.model.ChampionViewModel

// DetailFragment is the fragment that shows the details of a champion
class DetailFragment(champion: Champion) : Fragment() {
    private val champ = champion
    private val championViewModel: ChampionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.title = "Champion Details"
        return FragmentDetailBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.apply {
            champName.text = champ.name
            champDmg.text = getString(R.string.champion_dmg, champ.dmg)
            champRelease.text = getString(R.string.champion_release, champ.release)
            champRole.text = getString(R.string.champion_role, champ.role)
            champDescription.text = getString(R.string.champions_description)
            champImage.setImageResource(champ.splash)
        }
    }

}