package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.hiof.larseknu.playingwithfragments.R;
import no.hiof.larseknu.playingwithfragments.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    private List<Movie> movieList;

    public final static String MOVIE_INDEX = "movieIndex";
    private static final int DEFAULT_MOVIE_INDEX = 1;

    private TextView movieTitleView;
    private ImageView moviePosterImageView;
    private int movieIndex;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieList = Movie.getData();

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        movieTitleView = fragmentView.findViewById(R.id.movie_title);
        moviePosterImageView = fragmentView.findViewById(R.id.movie_poster);

        movieIndex = savedInstanceState == null? DEFAULT_MOVIE_INDEX : savedInstanceState.getInt(MOVIE_INDEX, DEFAULT_MOVIE_INDEX);
        setDisplayedMovieDetail(movieIndex);

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MOVIE_INDEX, movieIndex);
    }

    public void setDisplayedMovieDetail(int movieIndex) {
        this.movieIndex = movieIndex;
        movieList = Movie.getData();

        Movie movie = movieList.get(movieIndex);

        movieTitleView.setText(movie.getTitle());

        Drawable imagePoster = ContextCompat.getDrawable(getActivity(), movie.getImageId());
        if (imagePoster != null)
            moviePosterImageView.setImageDrawable(imagePoster);
    }
}
