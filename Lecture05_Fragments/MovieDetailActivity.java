package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.hiof.larseknu.playingwithfragments.R;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String MOVIE_ID_KEY = "movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        int id = getIntent().getIntExtra(MOVIE_ID_KEY, 1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieDetailFragment movieDetailFragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.movieDetailFragment);

        movieDetailFragment.setDisplayedMovieDetail(id);
    }
}
