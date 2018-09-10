package no.hiof.larseknu.playingwithmaterialdesign.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = LandscapeRecyclerAdapter.class.getSimpleName();

    private List<Landscape> landscapeList;
    private LayoutInflater inflater;


    public LandscapeRecyclerAdapter (Context context, List<Landscape> landscapeList) {
        inflater = LayoutInflater.from(context);

        this.landscapeList = landscapeList;
    }

    @Override
    public LandscapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.i(TAG, "onCreateViewHolder");
        View itemView = inflater.inflate(R.layout.landscape_list_item, parent, false);

        return new LandscapeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LandscapeViewHolder viewHolder, int position) {
        Log.i(TAG, "onBindViewHolder " + position);
        Landscape landscapeToDisplay = landscapeList.get(position);

        viewHolder.setLandscape(landscapeToDisplay, position);
        viewHolder.setListeners();
    }

    @Override
    public int getItemCount() {
        return landscapeList.size();
    }

    public void removeItem(int position) {
        landscapeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, landscapeList.size());
// 		  notifyDataSetChanged();
    }

    public void addItem(int position, Landscape landscape) {
        landscapeList.add(position, landscape);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, landscapeList.size());
//		  notifyDataSetChanged();
    }

    public class LandscapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView, descriptionTextView;
        private ImageView thumbnailImageView, deleteImageView, copyImageView;
        private int position;
        private Landscape landscaped;

        public LandscapeViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            copyImageView = itemView.findViewById(R.id.copyImageView);

            itemView.setOnClickListener(this);
        }

        public void setLandscape(Landscape landscape, int position) {
            titleTextView.setText(landscape.getTitle());
            descriptionTextView.setText(landscape.getDescription());
            thumbnailImageView.setImageResource(landscape.getImageID());

            landscaped = landscape;
            this.position = position;
        }

        public void setListeners() {
            deleteImageView.setOnClickListener(LandscapeViewHolder.this);
            copyImageView.setOnClickListener(LandscapeViewHolder.this);
        }

        @Override
        public void onClick(View view) {
            Log.i("onClick before operatio", position + " " + landscapeList.size());
            switch (view.getId()) {
                case R.id.deleteImageView:
                    removeItem(position);
                    break;

                case R.id.copyImageView:
                    addItem(position, landscaped);
                    break;
                default:
                    Toast.makeText(view.getContext(), "Item number " + getLayoutPosition() + " clicked",Toast.LENGTH_SHORT).show();

            }
            Log.i("onClick after operation", landscapeList.size() + "");

        }
    }
}
