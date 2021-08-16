package com.example.happylife.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.transition.TransitionInflater
import com.example.happylife.R
import com.example.happylife.databinding.FragmentPictureDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PictureDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PictureDetailFragment : Fragment() {

    lateinit var binding: FragmentPictureDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureDetailBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("name").let {
            view.findViewById<TextView>(R.id.tv_content).setText(it)
        }

        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
        ViewCompat.setTransitionName(binding.ivPicture, "picture")

    }
}