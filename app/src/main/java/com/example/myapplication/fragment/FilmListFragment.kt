package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.FilmAdapter
import com.example.myapplication.models.FilmList
import com.example.myapplication.viewmodel.FilmViewModel

class FilmListFragment : Fragment() {

    private lateinit var filmAdapter : FilmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_films_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val filmRecyclerView = view.findViewById<RecyclerView>(R.id.film_recycler_view)
        filmRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        filmRecyclerView.addItemDecoration(decoration)

        filmAdapter = FilmAdapter()
        filmRecyclerView.adapter = filmAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[FilmViewModel::class.java]
        viewModel.getFilmListObserver().observe(viewLifecycleOwner, Observer<FilmList> {
            if(it != null) {
                filmAdapter.setUpdatedFilm(it.results)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilmListFragment()
    }
}