package no.hiof.larseknu.playingwithfirebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import no.hiof.larseknu.playingwithfirebase.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;

    private TextView movieTitleTextView;
    private TextView movieReleaseDateTextView;
    private TextView movieSummaryTextView;
    private ImageView moviePosterImageView;
    private RatingBar movieRatingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieReleaseDateTextView = findViewById(R.id.movieYearTextView);
        movieSummaryTextView = findViewById(R.id.summaryTextView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieRatingBar = findViewById(R.id.movieRatingBar);
        moviePosterImageView = findViewById(R.id.moviePosterImageView);


    }


}
