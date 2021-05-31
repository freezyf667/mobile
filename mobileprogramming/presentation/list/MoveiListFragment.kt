package com.example.mobileprogramming.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogramming.R
import com.example.mobileprogramming.presentation.Singletons
import com.example.mobileprogramming.presentation.api.MovieApi
import com.example.mobileprogramming.presentation.api.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieListFragment : Fragment() {

    private val baseUrl: Any
        get() {
            TODO()
        }
    private lateinit var recyclerView: RecyclerView

    private val adapter = MovieAdapter(listOf(), ::onClickedMovie)

    private fun onClickedMovie(Movie: Movie){
        findNavController().navigate(R.id.navigateToMovieDetailFragment)
    }
    //7min38 Créer méthode onClickedMovie

    //private val sharedPref : SharedPreferences? = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.movie_recyclerview)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }


        callApi()

    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/4/discover/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApi = retrofit.create(MovieApi::class.java)


    private fun callApi() {
        Singletons.movieApi.getMovieList().enqueue(object : Callback<MovieListResponse> {
            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                //TODO("not implemented")
            }
            //49min

            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val movieListResponse: MovieListResponse= response.body()!!
                    showList(movieListResponse.results)
                }
            }
        })
    }

    private fun showList (movieList: List<Movie>) {
        adapter.updateList(movieList)
    }

    private fun onClickedMovie(id: Int) {
            findNavController().navigate(R.id.navigateToMovieDetailFragment, bundleOf(
                "movieId" to (id+1)
            ))
        }
}
