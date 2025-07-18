package com.example.testapplication.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ComposeViewModel : ViewModel() {

    private val _clickIncrement = MutableStateFlow(500)
    val clickIncrement: StateFlow<Int> = _clickIncrement

    private val _clickCount = MutableStateFlow(0)
    val clickCount: StateFlow<Int> = _clickCount

    private val _itemList = MutableStateFlow<List<String>>(emptyList())
    val itemList: StateFlow<List<String>> = _itemList

    fun onButtonClick() {
        _clickCount.update { it + 1 }
        repeat(_clickIncrement.value) {
            _clickCount.update { it + 1 }
            val newItem = "Elemento ${_itemList.value.size+1}"
            _itemList.update { it + newItem }
        }

    }
}
