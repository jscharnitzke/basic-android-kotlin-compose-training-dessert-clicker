package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class DessertUiState(
    @DrawableRes val currentDessertImage: Int,
    val currentDessertPrice: Int,
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
)
