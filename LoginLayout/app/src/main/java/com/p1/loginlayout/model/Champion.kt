package com.p1.loginlayout.model

import com.p1.loginlayout.R

// Class to create the champions
data class Champion(
    val id: Int?=-1,
    val name: String,
    val dmg: String,
    val release: String,
    val splash: Int,
    val role: String,
    val description: Int = R.string.champions_description
)