package no.hiof.larseknu.recyclerviewexercise.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import no.hiof.larseknu.recyclerviewexercise.model.Animal;


// TODO ARA 4: Set the class to extend RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder>
// TODO ARA 5: Override the needed methods
public class AnimalRecyclerAdapter  {
    private List<Animal> animalList;
    private LayoutInflater layoutInflater;

    public AnimalRecyclerAdapter(Context context, List<Animal> animalList) {
        // TODO ARA 6: Create an inflater
        // TODO ARA 7: Set the animalList
    }

    // TODO ARA 9: in onCreateViewHolder: Inflate the animal_list_item into a view
    // TODO ARA 10: in onCreateViewHolder: Create and return an AnimalViewHolder with the inflated view


    // TODO ARA 11: in onBindViewHolder: Get the animal in the current position from the list
    // TODO ARA 12: in onBindViewHolder: Set the animal in the viewHolder


    // TODO ARA 8: in getItemCount: Return the number of items in the list



    // TODO ARA 1: Create an internal class AnimalViewHolder that extends RecyclerView.ViewHolder
    // TODO ARA 2: In AnimalViewHolder: Get a reference to the ImageView and TextView in the constructor
    // TODO ARA 3: In AnimalViewHolder: Create a setAnimal(Animal animal) method, that inserts the data in the corresponding view

}
