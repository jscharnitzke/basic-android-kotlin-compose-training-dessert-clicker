package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState(
        Datasource.dessertList[0].imageId,
        Datasource.dessertList[0].price,
    ))
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()
    private val _desserts: List<Dessert> = Datasource.dessertList

    private fun determineDessertToShow() {
        var dessertToShow = _desserts.first()

        for(dessert in _desserts) {
            if(_uiState.value.dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }

        _uiState.update {currentState ->
            currentState.copy(
                currentDessertImage = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price,
            )
        }
    }

    fun sellDessert() {
        _uiState.update {currentState ->
            currentState.copy(
                revenue = currentState.revenue.plus(currentState.currentDessertPrice),
                dessertsSold = currentState.dessertsSold.inc(),
            )
        }

        determineDessertToShow()
    }
}