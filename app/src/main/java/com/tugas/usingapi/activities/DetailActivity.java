package com.tugas.usingapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tugas.usingapi.R;
import com.tugas.usingapi.models.movie.MovieModel;
import com.tugas.usingapi.networks.ApiClient;
import com.tugas.usingapi.networks.ApiInterface;
import com.tugas.usingapi.networks.Const;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private MovieModel model;
    private ImageView ivItemImg, ivCoverImg;
    private TextView tvItemSynopsis, tvItemTitle, tvExpandableBtn, tvRating, tvVoteCount, tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivCoverImg = findViewById(R.id.iv_cover_img);
        ivItemImg = findViewById(R.id.iv_item_image);
        tvItemTitle = findViewById(R.id.tv_item_title);
        tvItemSynopsis = findViewById(R.id.tv_item_synopsis);
        tvItemSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvExpandableBtn.getText().toString().equalsIgnoreCase("less")){
                    tvItemSynopsis.setMaxLines(2);
                    tvExpandableBtn.setText("more");
                } else {
                    tvItemSynopsis.setMaxLines(Integer.MAX_VALUE);
                    tvExpandableBtn.setText("less");
                }
            }
        });
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvVoteCount = findViewById(R.id.tv_voter);
        tvRating = findViewById(R.id.tv_rating);
        tvExpandableBtn = findViewById(R.id.tv_expandableBtn);
        tvExpandableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvExpandableBtn.getText().toString().equalsIgnoreCase("less")){
                    tvItemSynopsis.setMaxLines(2);
                    tvExpandableBtn.setText("more");
                } else {
                    tvItemSynopsis.setMaxLines(Integer.MAX_VALUE);
                    tvExpandableBtn.setText("less");
                }
            }
        });

        loadData(getIntent().getStringExtra("ID"));


    }

    private void loadData(String id) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<MovieModel> movieModelCall = apiInterface.getMovie(id, Const.API_KEY);
        movieModelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    model = response.body();
                    addData();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void addData() {
        Glide.with(DetailActivity.this).load(Const.IMG_URL_ORI + model.getImgUrl()).into(ivCoverImg);
        Glide.with(DetailActivity.this).load(Const.IMG_URL_300 + model.getImgUrl()).into(ivItemImg);
        tvItemTitle.setText(model.getTitle());
        tvRating.setText(model.getRating());
        tvItemSynopsis.setText(model.getOverview());
        tvVoteCount.setText(model.getVoteCount());
        tvReleaseDate.setText(model.getReleaseDate());
    }

    private void expandableTextView() {
        if (tvExpandableBtn.getText().toString().equalsIgnoreCase("less")){
            tvItemSynopsis.setMaxLines(2);
            tvExpandableBtn.setText("more");
        } else {
            tvItemSynopsis.setMaxLines(Integer.MAX_VALUE);
            tvExpandableBtn.setText("less");
        }
    }

}