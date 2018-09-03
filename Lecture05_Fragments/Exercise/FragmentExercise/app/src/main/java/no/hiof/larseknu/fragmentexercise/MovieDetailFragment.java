package no.hiof.larseknu.fragmentexercise;


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

import no.hiof.larseknu.fragmentexercise.model.Movie;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    private List<Movie> movieList;

    private TextView movieTitleTextView;
    private ImageView moviePosterImageView;
    private int movieIndex;

    // TODO 02.01: Create an corresponding layout fragment_movie_detail.xml
    // it should at least have a movie title and a movie poster

    // TODO 02.02: Create a empty constructor

    // TODO 02.03: Override onCreateView() fragment lifecycle method
    // TODO 02.04: In onCreateView: Inflate the layout fragment_movie_detail layout
    // TODO 02.05: In onCreateView: Get a reference to the views that holds the movie title and movie poster


    // TODO 02.02: Create a method that has an int movieIndex as parameter
    // (this gives the Activity the possibility of sending us that information)
    // TODO 02.03: Get the selected movie
    // TODO 02.03: Fill the views with the movie title and movie poster from the selected movie

    // TODO 04.01: Add handling for saving the instance (we want to see the same movie if we rotate the screen)
}
