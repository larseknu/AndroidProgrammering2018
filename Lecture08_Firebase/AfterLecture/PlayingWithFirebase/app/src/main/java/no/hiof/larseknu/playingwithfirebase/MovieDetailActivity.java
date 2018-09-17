package no.hiof.larseknu.playingwithfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import no.hiof.larseknu.playingwithfirebase.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String MOVIE_UID = "movie_uid";

    private Movie movie;

    private TextView movieTitleTextView;
    private TextView movieReleaseDateTextView;
    private TextView movieSummaryTextView;
    private ImageView moviePosterImageView;
    private RatingBar movieRatingBar;

    private DatabaseReference ratingReference;
    private FirebaseDatabase firebaseDatabase;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        String movieUid = getIntent().getStringExtra(MOVIE_UID);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference movieReference = firebaseDatabase.getReference("movies").child(movieUid);

        firebaseAuth = FirebaseAuth.getInstance();

        ratingReference = firebaseDatabase.getReference("ratings")
                .child(firebaseAuth.getCurrentUser().getUid())
                .child(movieUid);

        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieReleaseDateTextView = findViewById(R.id.movieYearTextView);
        movieSummaryTextView = findViewById(R.id.summaryTextView);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        movieRatingBar = findViewById(R.id.movieRatingBar);
        moviePosterImageView = findViewById(R.id.moviePosterImageView);

        movieRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    rating = Math.round(rating);
                    ratingReference.setValue(rating);
                    ratingBar.setRating(rating);
                }
            }
        });

        movieReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movie = dataSnapshot.getValue(Movie.class);
                movie.setUid(dataSnapshot.getKey());

                movieTitleTextView.setText(movie.getTitle());
                movieReleaseDateTextView.setText(movie.getReleaseDate());
                movieSummaryTextView.setText(movie.getDescription());

                if (movie.getPosterUrl() != null) {
                    Glide.with(MovieDetailActivity.this)
                            .load(movie.getPosterUrl())
                            .into(moviePosterImageView);
                }

                addRatingEventListener(ratingReference);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addRatingEventListener(DatabaseReference ratingDatabaseReference) {
        ratingDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    long rating = (long) dataSnapshot.getValue();
                    movieRatingBar.setRating(rating);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
