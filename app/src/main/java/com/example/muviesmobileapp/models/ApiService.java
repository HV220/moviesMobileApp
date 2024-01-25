package com.example.muviesmobileapp.models;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=ZV3GJNZ-R804BWF-PQNZERM-F865TK4&limit=30")
    Single<MovieResponse> loadMovies(@Query("page")int page);

    @GET("movie?token=ZV3GJNZ-R804BWF-PQNZERM-F865TK4&selectFields=videos")
    Single<TrailerMovieResponse> loadTrailersMovies(@Query("id")int id);
}
