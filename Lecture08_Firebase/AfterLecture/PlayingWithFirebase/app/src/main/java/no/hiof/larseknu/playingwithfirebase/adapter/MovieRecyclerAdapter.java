package no.hiof.larseknu.playingwithfirebase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import no.hiof.larseknu.playingwithfirebase.R;
import no.hiof.larseknu.playingwithfirebase.model.Movie;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {
    private List<Movie> data;
    private LayoutInflater inflater;

    private View.OnClickListener clickListener;

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MovieRecyclerAdapter(Context context, List<Movie> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currentObj = data.get(position);
        holder.setData(currentObj);

        if (clickListener != null) {
            holder.itemView.setOnClickListener(clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            poster = itemView.findViewById(R.id.movie_poster);
        }

        public void setData(Movie current) {
            this.title.setText(current.getTitle());

            String posterUrl = current.getPosterUrl();

            if (posterUrl != null && !posterUrl.equals("")) {
                Glide.with(poster.getContext())
                        .load(posterUrl)
                        .into(poster);
            }
            else
                poster.setImageResource(R.drawable.poster_placeholder);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
