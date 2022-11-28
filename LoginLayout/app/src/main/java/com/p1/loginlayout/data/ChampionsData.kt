package com.p1.loginlayout.data

import com.p1.loginlayout.R
import com.p1.loginlayout.model.Champion

object ChampionsData {
    fun getChampionsData(): MutableList<Champion> {

        return mutableListOf(
            Champion(null, "Aatrox", "Daño Fisico", "2013", R.drawable.aatrox, "Fighter"),
            Champion(null, "Annie", "Daño Magico", "2009", R.drawable.annie, "Mage"),
            Champion(null, "Lillia", "Daño Magico", "2020", R.drawable.lillia, "Mage"),
            Champion(null, "Vi", "Daño Fisico", "2012", R.drawable.vi, "Fighter"),
            Champion(null, "Zeri", "Daño Fisico", "2022", R.drawable.zeri, "Fighter")
        )


    }
}