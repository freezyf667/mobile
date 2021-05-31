package com.example.mobileprogramming.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileprogramming.R

class MovieAdapter(private var dataSet: List<Movie>, var listener: ((Int) -> Unit)? = null) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.movie_name)
            imageView = view.findViewById(R.id.movie_img)
            }
        }

    fun updateList(list: List<Movie>){
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movie_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val movie : Movie =dataSet[position]
        viewHolder.textView.text = movie.title
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
        Glide
            .with(viewHolder.itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/movie/${position + 1}.png")//lien images entre (), 1h03
            .centerCrop()
            .into(viewHolder.imageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}