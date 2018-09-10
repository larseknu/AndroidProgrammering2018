package no.hiof.larseknu.playingwithmaterialdesign.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import no.hiof.larseknu.playingwithmaterialdesign.R;
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape;

public class LandscapeRecyclerAdapter extends RecyclerView.Adapter<LandscapeRecyclerAdapter.LandscapeViewHolder> {
    private List<Landscape> landscapeList;
    private LayoutInflater inflater;


    public LandscapeRecyclerAdapter (Context context, List<Landscape> landscapeList) {
        inflater = LayoutInflater.from(context);

        this.landscapeList = landscapeList;
    }

    @Override
    public LandscapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = inflater.inflate(R.layout.landscape_list_item, parent, false);

        return new LandscapeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LandscapeViewHolder viewHolder, int position) {
        Landscape landscapeToShow = landscapeList.get(position);
        viewHolder.setLandscape(landscapeToShow);
    }

    @Override
    public int getItemCount() {
        return landscapeList.size();
    }


    public class LandscapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LandscapeRecyclerAdapter adapter;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView thumbnailImageView;

        public LandscapeViewHolder(View itemView, LandscapeRecyclerAdapter adapter) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);

            this.adapter = adapter;

            itemView.setOnClickListener(this);
        }

        public void setLandscape(Landscape landscape) {
            titleTextView.setText(landscape.getTitle());
            descriptionTextView.setText(landscape.getDescription());
            thumbnailImageView.setImageResource(landscape.getImageID());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Item number " + getLayoutPosition() + " clicked",Toast.LENGTH_SHORT).show();
        }
    }
}
