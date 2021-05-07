package com.tugas.usingapi.networks;

import com.tugas.usingapi.models.NowPlayingResponse;
import com.tugas.usingapi.models.UpcomingResponse;
import com.tugas.usingapi.models.movie.MovieModel;
import com.tugas.usingapi.models.tv_show.TvShowModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<MovieModel> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );

    @GET("tv/airing_today")
    Call<UpcomingResponse> getUpcomingResponseCall (
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TvShowModel> getTvShow(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );
}
