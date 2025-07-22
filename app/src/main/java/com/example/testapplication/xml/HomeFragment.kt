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
import com.example.testapplication.model.SharedState

class HomeFragment : Fragment() {

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

        textAdapter = SimpleTextAdapter(SharedState.itemList)
        imageAdapter = ImageAdapter(SharedState.imageList)

        addTextButton.text = "Aggiungi\n${SharedState.clickIncrement} elementi"
        addImageButton.text = "Aggiungi\n${SharedState.clickIncrement} immagini"

        addTextButton.setOnClickListener {
            SharedState.clickCount++
            repeat(SharedState.clickIncrement) {
                val newItem = "Elemento ${SharedState.itemList.size + 1}"
                SharedState.itemList.add(newItem)
            }
            recyclerView.adapter = textAdapter
            textAdapter.notifyDataSetChanged()
        }

        addImageButton.setOnClickListener {
            SharedState.clickCount++
            repeat(SharedState.clickIncrement) {
                val newImage = ImageItem(SharedState.imageList.size + 1, R.drawable.test)
                SharedState.imageList.add(newImage)
            }
            recyclerView.adapter = imageAdapter
            imageAdapter.notifyDataSetChanged()
        }
    }
}
