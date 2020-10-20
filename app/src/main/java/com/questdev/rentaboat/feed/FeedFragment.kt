package com.questdev.rentaboat.feed


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.questdev.rentaboat.HomeFragmentDirections
import com.questdev.rentaboat.R
import kotlinx.android.synthetic.main.fragment_feed.view.*

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        view.rvFeedList.adapter = FeedAdapter(BOATS, ::onFeedClick)
        view.rvFeedList.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

        return view
    }

    private fun onFeedClick(boatId: Int) {
        /*val args = Bundle()
        args.putInt("id", boatId)
        activity?.findNavController(R.id.nav_container)?.navigate(R.id.feedFragment, args)*/

        val action = HomeFragmentDirections.actionHomeFragmentToBoatFragment(boatId)
        activity?.findNavController(R.id.nav_container)?.navigate(action)
    }

}
