package com.fuadhev.goldenpaytask.people.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fuadhev.goldenpaytask.databinding.FragmentPeopleBinding
import com.fuadhev.goldenpaytask.people.ui.adapter.PersonAdapter
import com.fuadhev.goldenpaytask.people.ui.viewmodel.PeopleUiState
import com.fuadhev.goldenpaytask.people.ui.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding
    private val viewModel by viewModels<PeopleViewModel>()
    private val adapter by lazy {
        PersonAdapter(emptyList()) {

            findNavController().navigate(
                PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(
                    it
                )
            )

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPeople.adapter = adapter

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                handleState(it)
            }
        }
    }


    private fun handleState(state: PeopleUiState) {
        if (state.isLoading) {
            binding.loadingProgress.visibility = View.VISIBLE
        } else {
            binding.loadingProgress.visibility = View.GONE
        }

        adapter.updatePeopleList(state.peopleItemList)

        state.toastMessage?.let {
            Toast.makeText(requireContext(), getString(it.messageId), Toast.LENGTH_SHORT).show()
            state.toastMessage = null
        }
    }

}