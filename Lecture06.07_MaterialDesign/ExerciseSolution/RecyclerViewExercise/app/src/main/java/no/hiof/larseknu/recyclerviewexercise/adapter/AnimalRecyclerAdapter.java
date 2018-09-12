package no.hiof.larseknu.recyclerviewexercise.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.hiof.larseknu.recyclerviewexercise.R;
import no.hiof.larseknu.recyclerviewexercise.model.Animal;

public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder> {
    private List<Animal> animalList;
    private LayoutInflater layoutInflater;

    public AnimalRecyclerAdapter(Context context, List<Animal> animalList) {
        layoutInflater = LayoutInflater.from(context);

        this.animalList = animalList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = layoutInflater.inflate(R.layout.animal_list_item, parent, false);

        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder viewHolder, int position) {
        Animal animal = animalList.get(position);

        viewHolder.setAnimal(animal);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {
        private ImageView animalImageView;
        private TextView animalNameTextView;

        public AnimalViewHolder(View itemView) {
            super(itemView);

           animalImageView = itemView.findViewById(R.id.animalImageView);
           animalNameTextView = itemView.findViewById(R.id.animalNameTextView);
        }

        public void setAnimal(Animal animal) {
            animalImageView.setImageResource(animal.getImageId());
            animalNameTextView.setText(animal.getName());
        }
    }
}
