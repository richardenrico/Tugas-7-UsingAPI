package com.tugas.usingapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tugas.usingapi.R;
import com.tugas.usingapi.helper.LastEpisode;
import com.tugas.usingapi.models.tv_show.TvShowModel;
import com.tugas.usingapi.networks.ApiClient;
import com.tugas.usingapi.networks.ApiInterface;
import com.tugas.usingapi.networks.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUpcomingActivity extends AppCompatActivity {
    private TvShowModel tvShowModel;
    private ImageView ivItemImg, ivCoverImg;
    private TextView tvItemSynopsis, tvItemTitle, tvExpandableBtn, tvRating, tvVoter, tvReleaseDate, tvReleaseDateTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_upcoming);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivCoverImg = findViewById(R.id.iv_cover_img);
        ivItemImg = findViewById(R.id.iv_item_image);
        tvItemTitle = findViewById(R.id.tv_item_title);
        tvRating = findViewById(R.id.tv_rating);
        tvVoter = findViewById(R.id.tv_voter);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvReleaseDateTitle = findViewById(R.id.tv_release_date_title);
        tvReleaseDateTitle.setText("Last Episode");
        tvItemSynopsis = findViewById(R.id.tv_item_synopsis);
        tvExpandableBtn = findViewById(R.id.tv_expandableBtn);


        loadData(getIntent().getStringExtra("ID"));

        tvItemSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableTextView();
            }
        });

        tvExpandableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableTextView();
            }
        });
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

    private void loadData(String id) {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<TvShowModel> tvShowModelCall = apiInterface.getTvShow(id, Const.API_KEY);
        tvShowModelCall.enqueue(new Callback<TvShowModel>() {
            @Override
            public void onResponse(Call<TvShowModel> call, Response<TvShowModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tvShowModel = response.body();
                    addData();
                } else {
                    Toast.makeText(DetailUpcomingActivity.this, "is response but Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TvShowModel> call, Throwable t) {
                System.out.println("ERROR:" + t.getMessage());
                Toast.makeText(DetailUpcomingActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addData() {
        int nol = 0;
        Glide.with(DetailUpcomingActivity.this)
                .load(Const.IMG_URL_ORI + tvShowModel.getImgUrl())
                .into(ivCoverImg);
        Glide.with(DetailUpcomingActivity.this)
                .load(Const.IMG_URL_300 + tvShowModel.getImgUrl())
                .into(ivItemImg);
        tvItemTitle.setText(tvShowModel.getTitle());
        tvRating.setText(tvShowModel.getRating());
        tvItemSynopsis.setText(tvShowModel.getOverview());
        tvVoter.setText(tvShowModel.getVoter());
        tvReleaseDate.setText(tvShowModel.getReleaseDate());

    }
}