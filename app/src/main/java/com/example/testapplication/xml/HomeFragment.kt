package com.example.testapplication.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.adapter.SimpleTextAdapter
import com.example.testapplication.model.ImageItem

class HomeFragment : Fragment() {

    private val itemList = mutableListOf<String>()
    private val imageList = mutableListOf<ImageItem>()
    private var clickCount = 0
    private val clickIncrement = 10

    private lateinit var recyclerView: RecyclerView
    private lateinit var textAdapter: SimpleTextAdapter
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val addTextButton = view.findViewById<Button>(R.id.button_add_simple)
        val addImageButton = view.findViewById<Button>(R.id.button_add_image)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        textAdapter = SimpleTextAdapter(itemList)
        imageAdapter = ImageAdapter(imageList)

        addTextButton.setOnClickListener {
            clickCount++
            repeat(clickIncrement) {
                val newItem = "Elemento ${itemList.size + 1}"
                itemList.add(newItem)
            }
            recyclerView.adapter = textAdapter
            textAdapter.notifyDataSetChanged()
        }

        addImageButton.setOnClickListener {
            clickCount++
            repeat(clickIncrement) {
                val newImage = ImageItem(imageList.size + 1, R.drawable.test)
                imageList.add(newImage)
            }
            recyclerView.adapter = imageAdapter
            imageAdapter.notifyDataSetChanged()
        }
    }
}
