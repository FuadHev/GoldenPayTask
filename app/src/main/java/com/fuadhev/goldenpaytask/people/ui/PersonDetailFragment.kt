package com.fuadhev.goldenpaytask.people.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.fuadhev.goldenpaytask.R
import com.fuadhev.goldenpaytask.databinding.FragmentPersonDetailBinding


class PersonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPersonDetailBinding
    val bundle: PersonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personDetails = bundle.personDetails
        binding.txtPersonName.text = personDetails.name

    }

}