package com.example.testapplication.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ComposeViewModel : ViewModel() {

    private val _clickIncrement = MutableStateFlow(1)
    val clickIncrement: StateFlow<Int> = _clickIncrement

    private val _clickCount = MutableStateFlow(0)
    val clickCount: StateFlow<Int> = _clickCount

    private val _itemList = MutableStateFlow<List<String>>(emptyList())
    val itemList: StateFlow<List<String>> = _itemList

    fun onButtonClick() {
        _clickCount.update { it + clickIncrement.value }
        val newItem = "Elemento ${_clickCount.value}"
        _itemList.update { it + newItem }
    }
}
