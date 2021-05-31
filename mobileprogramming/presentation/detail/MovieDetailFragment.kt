package com.example.mobileprogramming.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileprogramming.R
import com.example.mobileprogramming.presentation.Singletons
import com.example.mobileprogramming.presentation.api.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragment : Fragment() {

    private lateinit var textViewName: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.movie_detail_name)
        callApi()
    }

    private fun callApi() {
        val id : Int = arguments?.getInt( "movieId") ?: -1
        Singletons.movieApi.getMovieDetail(id).enqueue(object: Callback<MovieDetailResponse>{
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if(response.isSuccessful && response.body() !=null){
                    textViewName.text = response.body()!!.name
                }

            }
        })
    }
}
