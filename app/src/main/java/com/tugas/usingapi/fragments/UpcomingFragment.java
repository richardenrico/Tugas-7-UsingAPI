package com.tugas.usingapi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tugas.usingapi.R;
import com.tugas.usingapi.activities.DetailActivity;
import com.tugas.usingapi.activities.DetailUpcomingActivity;
import com.tugas.usingapi.adapters.UpcomingAdapter;
import com.tugas.usingapi.models.Upcoming;
import com.tugas.usingapi.models.UpcomingResponse;
import com.tugas.usingapi.networks.ApiClient;
import com.tugas.usingapi.networks.ApiInterface;
import com.tugas.usingapi.networks.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment implements UpcomingAdapter.OnItemClick {
    private RecyclerView recyclerView;
    private UpcomingAdapter adapter;
    private List<Upcoming> upcomings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        recyclerView = view.findViewById(R.id.rv_upcoming);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        loadData();

        return view;
    }

    private void loadData() {
        ApiInterface apiInterface = ApiClient.getRetrofit()
                .create(ApiInterface.class);

        Call<UpcomingResponse> upcomingResponseCall = apiInterface.getUpcomingResponseCall(Const.API_KEY);
        upcomingResponseCall.enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                if (response.isSuccessful() && response.body().getUpcomings() != null) {
                    upcomings = response.body().getUpcomings();
                    adapter = new UpcomingAdapter(upcomings,UpcomingFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        String s = upcomings.get(pos).getTitle();
        Intent intent = new Intent(getActivity(), DetailUpcomingActivity.class);
        intent.putExtra("ID", upcomings.get(pos).getId());
        intent.putExtra("TITLE", upcomings.get(pos).getTitle());
        startActivity(intent);
    }
}