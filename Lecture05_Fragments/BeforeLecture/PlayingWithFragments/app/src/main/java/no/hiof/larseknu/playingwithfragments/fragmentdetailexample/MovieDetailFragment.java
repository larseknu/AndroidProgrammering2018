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

import no.hiof.larseknu.playingwithfragments.model.Movie;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    private List<Movie> movieList;

    private TextView movieTitleTextView;
    private ImageView moviePosterImageView;
    private int movieIndex;
}
