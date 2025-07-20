package com.example.testapplication.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.model.SharedState

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val clickText = view.findViewById<TextView>(R.id.text_click_count)
        val simpleItemCountText = view.findViewById<TextView>(R.id.text_simple_list_size)
        val imageItemCountText = view.findViewById<TextView>(R.id.text_image_list_size)

        clickText.text = "Numero di click: ${SharedState.clickCount}"
        simpleItemCountText.text = "Elementi nella lista: ${SharedState.itemList.size}"
        imageItemCountText.text = "Elementi nella lista immagini: ${SharedState.imageList.size}"

        view.findViewById<Button>(R.id.button_reset_simple).setOnClickListener {
            SharedState.itemList.clear()
            simpleItemCountText.text = "Elementi nella lista: 0"
        }

        view.findViewById<Button>(R.id.button_reset_image).setOnClickListener {
            SharedState.imageList.clear()
            imageItemCountText.text = "Elementi nella lista immagini: 0"
        }
    }
}
