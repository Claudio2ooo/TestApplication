package com.example.testapplication.compose

import androidx.lifecycle.ViewModel
import com.example.testapplication.R
import com.example.testapplication.model.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ComposeViewModel : ViewModel() {

    private val _clickIncrement = MutableStateFlow(10)
    val clickIncrement: StateFlow<Int> = _clickIncrement

    private val _clickCount = MutableStateFlow(0)
    val clickCount: StateFlow<Int> = _clickCount

    private val _itemList = MutableStateFlow<List<String>>(emptyList())
    val itemList: StateFlow<List<String>> = _itemList

    private val _imageList = MutableStateFlow<List<ImageItem>>(emptyList())
    val imageList: StateFlow<List<ImageItem>> = _imageList

    fun resetSimpleList() {
        _itemList.update { emptyList() }
    }

    fun resetImageList() {
        _imageList.update { emptyList() }
    }

    fun onButtonClickSimpleItem() {
        _clickCount.update { it + 1 }
        repeat(_clickIncrement.value) {
            val newItem = "Elemento ${_itemList.value.size+1}"
            _itemList.update { it + newItem }
        }
    }

    fun onButtonClickImageItem() {
        _clickCount.update { it + 1 }
        repeat(_clickIncrement.value) {
            val newItem = ImageItem(id = _imageList.value.size + 1, drawableRes = R.drawable.test)
            _imageList.update { it + newItem }
        }
    }

    fun setClickIncrement(value: Int) {
        _clickIncrement.value = value
    }
}
