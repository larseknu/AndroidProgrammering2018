package no.hiof.larseknu.playingwithnavigationdrawer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.hiof.larseknu.playingwithnavigationdrawer.R;
import no.hiof.larseknu.playingwithnavigationdrawer.model.Movie;

public class CollectionRecyclerAdapter extends RecyclerView.Adapter<CollectionRecyclerAdapter.MovieViewHolder> {

    private static final String TAG = CollectionRecyclerAdapter.class.getSimpleName();

    private List<Movie> mData;
    private LayoutInflater mInflater;

    public CollectionRecyclerAdapter(Context context, List<Movie> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currentObj = mData.get(position);
        holder.setData(currentObj);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            //title       = (TextView)  itemView.findViewById(R.id.movie_title);
            poster    = (ImageView) itemView.findViewById(R.id.movie_poster);
        }

        public void setData(Movie current) {
            //this.title.setText(current.getTitle());
            this.poster.setImageResource(current.getImageId());
        }
    }
}
