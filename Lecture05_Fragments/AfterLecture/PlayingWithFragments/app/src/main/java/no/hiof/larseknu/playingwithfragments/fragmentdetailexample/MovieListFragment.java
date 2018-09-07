package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import no.hiof.larseknu.playingwithfragments.R;
import no.hiof.larseknu.playingwithfragments.adapter.MovieRecyclerAdapter;
import no.hiof.larseknu.playingwithfragments.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private OnMovieFragmentInteractionListener listener;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        movieList = Movie.getData();
        setUpRecyclerView(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            listener = (OnMovieFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        listener = null;
    }

    public void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(getContext(), movieList, new MovieRecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), movieList.get(position).getTitle() + " clicked", Toast.LENGTH_SHORT).show();

                listener.onMovieSelected(position);
            }
        });

        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2); // (Context context, int spanCount)
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public interface OnMovieFragmentInteractionListener {
        void onMovieSelected(int id);
    }
}
