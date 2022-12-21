package com.p1.loginlayout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.R
import com.p1.loginlayout.SwipeToDeleteCallback
import com.p1.loginlayout.model.ChampionViewModel
import com.p1.loginlayout.recyclers.RecyclerViewAdapter

// MyListFragment is the fragment that shows the list of champions
class MyListFragment(dbHelper: ChampionsDBHelper) : Fragment() {
    private val db = dbHelper

    private val championsViewModel: ChampionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_my_list, container, false)
        var recyclerView: RecyclerView = v.findViewById(R.id.recyclerList);
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerViewAdapter(db.listChamps(), context, db)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        activity?.title = getString(R.string.list_fragment)

        val swipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val adapter = recyclerView.adapter as RecyclerViewAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return v.rootView

    }
}