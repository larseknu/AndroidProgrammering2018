package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.hiof.larseknu.playingwithfragments.R;

public class MovieListActivity extends AppCompatActivity  implements MovieListFragment.OnMovieFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setTitle("FragmentDetailExample");
    }

    @Override
    public void onMovieSelected(int movieId) {
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MovieDetailFragment movieDetailFragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.movieDetailFragment);

            movieDetailFragment.setDisplayedMovieDetail(movieId);
        }
        else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.MOVIE_ID_KEY, movieId);
            startActivity(intent);
        }
    }
}
