package com.my.kinopoisk.features.favouritefilmscreen.presenter.ui

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
import com.my.kinopoisk.databinding.FragmentFavouriteFilmScreenBinding
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteScreenState
import com.my.kinopoisk.features.favouritefilmscreen.presenter.ui.recyclerview.FavoriteFilmAdapter
import com.my.kinopoisk.features.favouritefilmscreen.presenter.vm.FavoriteFilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFilmScreenFragment : Fragment() {

    private val viewModel: FavoriteFilmsViewModel by viewModels()

    private var _binding: FragmentFavouriteFilmScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteFilmAdapter: FavoriteFilmAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteFilmScreenBinding.inflate(inflater, container, false)
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
                                    searchFavoriteFilm(newText)
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

    fun searchFavoriteFilm(query: String) {
        val searchQuery = "%$query%"
        Log.d("dfsdfsdf","AAA: $searchQuery")
    }

    private fun setUpRecyclerView() {
        favoriteFilmAdapter = FavoriteFilmAdapter()
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = favoriteFilmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state: FavoriteScreenState ->
                    when(state) {
                        is FavoriteScreenState.DataLoaded -> {
                            favoriteFilmAdapter.submitList(state.items)
                        }
                        FavoriteScreenState.Error -> {}
                        FavoriteScreenState.Initial -> {}
                        FavoriteScreenState.Loading -> {}
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