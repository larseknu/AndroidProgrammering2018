package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.hiof.larseknu.playingwithfragments.R;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setTitle("FragmentDetailExample");

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieListFragment movieListFragment = new MovieListFragment();

        fragmentManager.beginTransaction().add(R.id.leftFragmentContainter, movieListFragment).commit();


    }
}
