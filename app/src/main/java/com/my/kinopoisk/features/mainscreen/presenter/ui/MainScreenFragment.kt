package com.my.kinopoisk.features.mainscreen.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.databinding.FragmentMainScreenBinding
import com.my.kinopoisk.features.mainscreen.presenter.model.MainScreenState
import com.my.kinopoisk.features.mainscreen.presenter.ui.recyclerview.FilmAdapter
import com.my.kinopoisk.features.mainscreen.presenter.vm.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var filmAdapter: FilmAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observerFlow()
    }

    private fun setUpRecyclerView() {
        filmAdapter = FilmAdapter()
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state: MainScreenState ->
                    when(state) {
                        is MainScreenState.Initial -> {}
                        is MainScreenState.Loading -> {}
                        is MainScreenState.Error -> {}
                        is MainScreenState.Dataloaded -> {
                            filmAdapter.submitList(state.items)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}