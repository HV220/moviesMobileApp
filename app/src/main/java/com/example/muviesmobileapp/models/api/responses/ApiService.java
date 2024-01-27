package com.example.muviesmobileapp.models.api.responses;

import com.example.muviesmobileapp.models.api.responses.aboutmovie.reviews.ReviewResponse;
import com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers.TrailerResponse;
import com.example.muviesmobileapp.models.api.responses.movies.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=ZV3GJNZ-R804BWF-PQNZERM-F865TK4&limit=30")
    Single<MovieResponse> loadMovies(@Query("page")int page);

    @GET("movie?token=ZV3GJNZ-R804BWF-PQNZERM-F865TK4&selectFields=videos")
    Single<TrailerResponse> loadTrailersMovies(@Query("id")int id);

    @GET("review?token=ZV3GJNZ-R804BWF-PQNZERM-F865TK4&limit=5")
    Single<ReviewResponse> loadReviewResponse(@Query("movieId")int movieId);
}
