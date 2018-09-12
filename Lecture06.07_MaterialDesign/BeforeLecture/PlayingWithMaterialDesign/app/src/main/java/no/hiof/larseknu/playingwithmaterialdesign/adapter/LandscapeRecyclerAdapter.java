package no.hiof.larseknu.playingwithmaterialdesign.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import no.hiof.larseknu.playingwithmaterialdesign.R;
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape;

public class LandscapeRecyclerAdapter extends RecyclerView.Adapter<LandscapeRecyclerAdapter.LandscapeViewHolder> {
    public LayoutInflater inflater;
    public List<Landscape> landscapeList;

    public LandscapeRecyclerAdapter(Context context, List<Landscape> landscapeList) {
        inflater = LayoutInflater.from(context);
        this.landscapeList = landscapeList;
    }

    @Override
    public LandscapeViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = inflater.inflate(R.layout.landcsape_list_item, parent, false);

        return new LandscapeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LandscapeViewHolder landscapeViewHolder, int position) {
        Landscape landscape = landscapeList.get(position);

        landscapeViewHolder.setLandscapeData(landscape);
    }

    @Override
    public int getItemCount() {
        return landscapeList.size();
    }

    class LandscapeViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;
        TextView titleTextView;
        TextView descTextView;

        public LandscapeViewHolder(View itemView) {
            super(itemView);

            thumbnailImageView = itemView.findViewById(R.id.landscapeImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
        }

        public void setLandscapeData(Landscape landscapeData) {
            thumbnailImageView.setImageResource(landscapeData.getImageID());
            titleTextView.setText(landscapeData.getTitle());
            descTextView.setText(landscapeData.getDescription());
        }
    }
}
