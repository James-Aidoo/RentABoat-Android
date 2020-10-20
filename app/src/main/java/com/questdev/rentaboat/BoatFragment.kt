package com.questdev.rentaboat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.questdev.rentaboat.feed.BOATS
import com.questdev.rentaboat.feed.getBoat
import kotlinx.android.synthetic.main.fragment_boat.view.*

/**
 * A simple [Fragment] subclass.
 */
class BoatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val id = arguments?.getInt("id") ?: error("id not found")
        //val id = arguments?.let { BoatFragmentArgs.fromBundle(it).id } ?: error("no id found")
        val id = arguments?.getString("id_dl")?.toInt() ?: arguments?.let {
            BoatFragmentArgs.fromBundle(
                it
            ).id
        }
        val boat = BOATS.getBoat(id!!)

        val view = inflater.inflate(R.layout.fragment_boat, container, false)
        view.imgBoat.setImageResource(boat.picture)
        view.tvName.text = boat.name
        view.tvLocation.text = boat.location
        view.tvPrice.text = boat.price

        return view
    }


}
