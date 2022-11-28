package com.p1.loginlayout.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.p1.loginlayout.data.ChampionsData

// ChampionViewModel is used to store the data of the champions
class ChampionViewModel() : ViewModel() {
    private var _currentChampion: MutableLiveData<Champion> = MutableLiveData()
    val currentChampion: LiveData<Champion>
        get() = _currentChampion

    private var _championsData: MutableList<Champion> = mutableListOf()
    val championsData: MutableList<Champion>
        get() = _championsData

    init {
        _championsData = ChampionsData.getChampionsData()
        _currentChampion.value = _championsData[0]
    }

    fun setCurrentChampion(champion: Champion) {
        _currentChampion.value = champion
    }

    fun insertChamp(champion: Champion) {
        _championsData.add(champion)
    }


}