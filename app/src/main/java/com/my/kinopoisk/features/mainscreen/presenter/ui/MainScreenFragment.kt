package com.my.kinopoisk.features.mainscreen.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.R
import com.my.kinopoisk.databinding.FragmentMainScreenBinding
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi
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

    private val insertDeleteFilmListener: (
        filmUi: FilmUi
    ) -> Unit = { filmUi ->
        if (filmUi.isSavedToDataBase) {
            viewModel.removeFromFavoriteFilm(filmUi)
        } else {
            viewModel.addToFavoriteFilm(filmUi)
        }
    }

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
        setUpToolBar()
    }

    private fun setUpToolBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        val searchView = menuItem.actionView as? SearchView
                        searchView?.queryHint = "My queryHint"
                        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                            override fun onQueryTextSubmit(p0: String?): Boolean {
                                searchView.clearFocus()
                                return true
                            }
                            override fun onQueryTextChange(newText: String?): Boolean {
                                if (newText != null) {
                                    searchFilm(newText)
                                }
                                return true
                            }
                        })
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun searchFilm(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchFilm(searchQuery)
    }

    private fun setUpRecyclerView() {
        filmAdapter = FilmAdapter(insertDeleteFilmListener)
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