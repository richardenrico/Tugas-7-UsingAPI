package com.tugas.usingapi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tugas.usingapi.R;
import com.tugas.usingapi.fragments.NowPlayingFragment;
import com.tugas.usingapi.fragments.PopularFragment;
import com.tugas.usingapi.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_movie);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.page_movie:
                getSupportActionBar().setTitle(getResources().getString(R.string.now_playing));
                fragment = new NowPlayingFragment();
                break;

            case R.id.page_tv_show:
                getSupportActionBar().setTitle(getResources().getString(R.string.upcoming));
                fragment = new UpcomingFragment();
                break;
            case R.id.page_library:
                getSupportActionBar().setTitle(getResources().getString(R.string.library));
                fragment = new PopularFragment();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}