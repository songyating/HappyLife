package com.example.happylife.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.happylife.R

/**
 * A simple [Fragment] subclass.
 * Use the [PictureDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PictureDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("name").let {
            view.findViewById<TextView>(R.id.tv_content).setText(it)
        }
    }
}