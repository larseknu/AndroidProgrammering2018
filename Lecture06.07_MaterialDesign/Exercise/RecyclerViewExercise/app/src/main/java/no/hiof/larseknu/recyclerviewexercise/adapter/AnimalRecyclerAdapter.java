package no.hiof.larseknu.recyclerviewexercise.adapter;


import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.hiof.larseknu.recyclerviewexercise.R;
import no.hiof.larseknu.recyclerviewexercise.model.Animal;


// ARA 4: Set the class to extend RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder>
// ARA 5: Override the needed methods
public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder>  {
    private List<Animal> animalList;
    private LayoutInflater layoutInflater;

    public AnimalRecyclerAdapter(Context context, List<Animal> animalList) {
        layoutInflater = LayoutInflater.from(context);

        this.animalList = animalList;
        // ARA 6: Create an inflater
        // ARA 7: Set the animalList
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.i("RecycleView", "onCreateViewHolder called " + position);
        // ARA 9: in onCreateViewHolder: Inflate the animal_list_item into a view
        // ARA 10: in onCreateViewHolder: Create and return an AnimalViewHolder with the inflated view
        View itemView = layoutInflater.inflate(R.layout.animal_list_item, viewGroup, false);

        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder animalViewHolder, int position) {
        Log.i("RecycleView", "onBindViewHolder called " + position);
        // ARA 11: in onBindViewHolder: Get the animal in the current position from the list
        Animal animalToDisplay = animalList.get(position);

        // ARA 12: in onBindViewHolder: Set the animal in the viewHolder
        animalViewHolder.setAnimal(animalToDisplay);
    }

    // ARA 8: in getItemCount: Return the number of items in the list
    @Override
    public int getItemCount() {
        return animalList.size();
    }


    class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView animalImageView;
        TextView nameTextView;

        public AnimalViewHolder(View itemView) {
            super(itemView);

            animalImageView = itemView.findViewById(R.id.animalImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        public void setAnimal(Animal animal) {
            animalImageView.setImageResource(animal.getImageId());
            nameTextView.setText(animal.getName());
        }
    }
    // ARA 1: Create an internal class AnimalViewHolder that extends RecyclerView.ViewHolder
    // ARA 2: In AnimalViewHolder: Get a reference to the ImageView and TextView in the constructor
    // ARA 3: In AnimalViewHolder: Create a setAnimal(Animal animal) method, that inserts the data in the corresponding view

}
