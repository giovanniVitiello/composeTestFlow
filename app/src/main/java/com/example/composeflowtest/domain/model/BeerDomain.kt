package com.example.composeflowtest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerDomain(
    val name: String?,
    val imageUrl: String?,
    val description: String?,
    val abv: String?,
    val ibu: String?,
    val firstBrewed: String?,
    val foodPairing: List<String>?,
    val brewersTips: String?
) : Parcelable
