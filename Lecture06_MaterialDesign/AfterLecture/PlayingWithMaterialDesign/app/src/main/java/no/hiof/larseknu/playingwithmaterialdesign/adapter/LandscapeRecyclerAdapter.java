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
        // Create a new inflater we need to inflate the layout for each list item
        inflater = LayoutInflater.from(context);

        // Set the list
        this.landscapeList = landscapeList;
    }

    @Override
    public LandscapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        // Log so we can see when the onCreateViewHolder method is called
        Log.i(TAG, "onCreateViewHolder");
        // Inflates the lanscape_list_item.xml to a view for us
        View itemView = inflater.inflate(R.layout.landscape_list_item, parent, false);

        // Create the viewholder with the corresponding view (list item)
        return new LandscapeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LandscapeViewHolder viewHolder, int position) {
        // Log so we can see when the bind method is called
        Log.i(TAG, "onBindViewHolder " + position);
        // Gets the landscape data we are going to use at the given position
        Landscape landscapeToDisplay = landscapeList.get(position);

        // Gives the landscape data and poistion to the viewholder
        // Makes it fill up the given position with the new data
        viewHolder.setLandscape(landscapeToDisplay, position);
        // Set listeners for the buttons/images in the view
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

            // Gets a reference to all the views we are going to use
            // or fill with data from the itemView
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            copyImageView = itemView.findViewById(R.id.copyImageView);

            // Set onclicklistener to the whole ViewHolder
            itemView.setOnClickListener(this);
        }

        public void setLandscape(Landscape landscape, int position) {
            // Fills the views with the given data
            titleTextView.setText(landscape.getTitle());
            descriptionTextView.setText(landscape.getDescription());
            thumbnailImageView.setImageResource(landscape.getImageID());

            // Stores a reference to the data and position
            landscaped = landscape;
            this.position = position;
        }

        public void setListeners() {
            // Sets listeners to the ImageViews we want to handle clicks on
            deleteImageView.setOnClickListener(LandscapeViewHolder.this);
            copyImageView.setOnClickListener(LandscapeViewHolder.this);
        }

        @Override
        public void onClick(View view) {
            // Logs the clicks with position and size of list
            Log.i("onClick before operatio", position + " " + landscapeList.size());
            // Checks which elements in the view that was clicked
            switch (view.getId()) {
                case R.id.deleteImageView:
                    // Removes item at the position
                    removeItem(position);
                    break;

                case R.id.copyImageView:
                    // Adds a new item at the given position
                    addItem(position, landscaped);
                    break;
                default:
                    Toast.makeText(view.getContext(), "Item number " + getLayoutPosition() + " clicked",Toast.LENGTH_SHORT).show();

            }
            // Logs the position and size of list after click
            Log.i("onClick after operation", landscapeList.size() + "");

        }
    }
}
