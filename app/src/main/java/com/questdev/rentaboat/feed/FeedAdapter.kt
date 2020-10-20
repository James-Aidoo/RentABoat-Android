package com.questdev.rentaboat.feed
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.questdev.rentaboat.R
import kotlinx.android.synthetic.main.feed_list_item.view.*

class FeedAdapter(private val boats: List<Boat>, private val onFeedClick: (Int) -> Unit) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boats[position], onFeedClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feed_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return boats.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val nameTextView = itemView.name
        private val locationTextView = itemView.location
        private val imageView = itemView.picture
        private val priceTextView = itemView.price

        fun bind(boat: Boat, onFeedClick: (Int) -> Unit) {
            nameTextView.text = boat.name
            locationTextView.text = boat.location
            imageView.setImageResource(boat.picture)
            priceTextView.text = boat.price

            itemView.setOnClickListener { onFeedClick(boat.id) }
        }
    }
}