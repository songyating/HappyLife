package com.example.happylife.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.happylife.R
import com.example.happylife.databinding.FragmentPictureListBinding
import com.example.happylife.databinding.FragmentVideoListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PictureListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PictureListFragment : Fragment() {
    lateinit var binding: FragmentPictureListBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureListBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btTranslation.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_picture_list_fragment_to_picture_detail_fragment)
        })

        binding.btSharedElement.setOnClickListener(View.OnClickListener {
            ViewCompat.setTransitionName(binding.ivPicture, "picture")
            val extra = FragmentNavigatorExtras(
                binding.ivPicture to "picture"
            )
            val directions =
                PictureListFragmentDirections.actionPictureListFragmentToPictureDetailFragment()
            findNavController().navigate(directions, extra)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PictureListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PictureListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}