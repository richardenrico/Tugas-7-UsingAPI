package com.tugas.usingapi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tugas.usingapi.R;
import com.tugas.usingapi.activities.DetailActivity;
import com.tugas.usingapi.adapters.NowPlayingAdapter;
import com.tugas.usingapi.models.NowPlaying;
import com.tugas.usingapi.models.NowPlayingResponse;
import com.tugas.usingapi.networks.ApiClient;
import com.tugas.usingapi.networks.ApiInterface;
import com.tugas.usingapi.networks.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements NowPlayingAdapter.OnItemClick {
    private static final String TAG = "NowPlayingFragment";
    private RecyclerView recyclerView;
    private NowPlayingAdapter adapter;
    private List<NowPlaying> nowPlayings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        recyclerView = view.findViewById(R.id.rv_now_playing);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        loadData();
        return view;
    }

    public void loadData() {
        ApiInterface apiInterface = ApiClient.getRetrofit()
                .create(ApiInterface.class);

        Call<NowPlayingResponse> nowPlayingResponseCall =  apiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingResponseCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null) {
                    nowPlayings = response.body().getNowPlayings();
                    adapter = new NowPlayingAdapter(nowPlayings, NowPlayingFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("ID", nowPlayings.get(pos).getId());
        intent.putExtra("TITLE", nowPlayings.get(pos).getTitle());
        startActivity(intent);
    }
}