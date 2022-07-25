package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.PlanetAdapter
import com.example.myapplication.models.PlanetList
import com.example.myapplication.viewmodel.PlanetViewModel

class PlanetListFragment: Fragment() {
    private lateinit var planetAdapter : PlanetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_planets_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val peopleRecyclerView = view.findViewById<RecyclerView>(R.id.planet_recycler_view)
        peopleRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        peopleRecyclerView.addItemDecoration(decoration)

        planetAdapter = PlanetAdapter()
        peopleRecyclerView.adapter = planetAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[PlanetViewModel::class.java]
        viewModel.getPeopleListObserver().observe(viewLifecycleOwner, Observer<PlanetList> {
            if(it != null) {
                planetAdapter.setUpdatedPlanet(it.results)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlanetListFragment()
    }
}