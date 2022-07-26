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
import com.example.myapplication.adapter.PeoplesAdapter
import com.example.myapplication.models.PeopleList
import com.example.myapplication.viewmodel.PeopleViewModel

class PeopleListFragment: Fragment() {
    private lateinit var peopleAdapter : PeoplesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_peoples_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val peopleRecyclerView = view.findViewById<RecyclerView>(R.id.people_recycler_view)
        peopleRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        peopleRecyclerView.addItemDecoration(decoration)

        peopleAdapter = PeoplesAdapter()
        peopleRecyclerView.adapter = peopleAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[PeopleViewModel::class.java]
        viewModel.getPeopleListObserver().observe(viewLifecycleOwner, Observer<PeopleList> {
            if(it != null) {
                peopleAdapter.setUpdatedPeople(it.results)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PeopleListFragment()
    }
}