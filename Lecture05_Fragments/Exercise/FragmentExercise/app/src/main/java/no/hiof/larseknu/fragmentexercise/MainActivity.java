package no.hiof.larseknu.fragmentexercise;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("FragmentDetailExample");
    }

    @Override
    public void onMovieSelected(int movieId) {
        // TODO 01.01: Create a activity_main.xml layout for landscape, which also includes the MovieDetailFragment

        // TODO 01.02: Check if the orientation is in landscape
        // TODO 01.03: If it's in landscape, send information to the MovieDetailFragment
        // TODO 01.04: If it's not in landscape, send the information to the MovieDetailActivity via an intent with extra
    }
}
