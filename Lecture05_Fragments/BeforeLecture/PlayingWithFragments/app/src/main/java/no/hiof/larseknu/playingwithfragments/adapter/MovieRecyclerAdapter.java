package no.hiof.larseknu.playingwithfragments.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import no.hiof.larseknu.playingwithfragments.R;
import no.hiof.larseknu.playingwithfragments.model.Movie;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Movie> movieList;
    private RecyclerViewClickListener clickListener;

    public MovieRecyclerAdapter(Context context, List<Movie> movieList, RecyclerViewClickListener clickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        // Retrieve the data for that position
        Movie currentMovie = movieList.get(position);
        // Add the data to the view
        movieViewHolder.setData(currentMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImageView;
        RecyclerViewClickListener onMovieClickListener;

        public MovieViewHolder(View itemView, RecyclerViewClickListener onMovieClickListener) {
            super(itemView);

            this.onMovieClickListener = onMovieClickListener;

            posterImageView = itemView.findViewById(R.id.movie_poster);


            itemView.setOnClickListener(this);
        }

        public void setData(Movie movie) {
            posterImageView.setImageResource(movie.getImageId());


        }

        @Override
        public void onClick(View view) {
            onMovieClickListener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

}
