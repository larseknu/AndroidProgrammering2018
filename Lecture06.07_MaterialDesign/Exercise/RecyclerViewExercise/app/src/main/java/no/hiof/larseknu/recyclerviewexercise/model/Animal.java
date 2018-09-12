package no.hiof.larseknu.recyclerviewexercise.model;


import java.util.ArrayList;
import java.util.List;

import no.hiof.larseknu.recyclerviewexercise.R;

public class Animal {

    private String name;
    private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<Animal> getData() {

        List<Animal> data = new ArrayList<>();

        int[] images = {
                R.drawable.ani_cat_one,
                R.drawable.ani_cat_two,
                R.drawable.ani_cat_three,
                R.drawable.ani_cat_four,
                R.drawable.ani_cat_five,
                R.drawable.ani_cat_six,
                R.drawable.ani_cat_seven,

                R.drawable.ani_dog_one,
                R.drawable.ani_dog_two,
                R.drawable.ani_dog_three,
                R.drawable.ani_dog_four,
                R.drawable.ani_dog_five,

                R.drawable.ani_deer_one,
                R.drawable.ani_deer_two,
                R.drawable.ani_deer_three,
                R.drawable.ani_deer_four,

                R.drawable.ani_badger_one,
                R.drawable.ani_badger_two,
                R.drawable.ani_badger_three,

                R.drawable.bird_parrot_one,
                R.drawable.bird_parrot_two,
                R.drawable.bird_parrot_three,
                R.drawable.bird_parrot_four,
                R.drawable.bird_parrot_five,
                R.drawable.bird_parrot_six,
                R.drawable.bird_parrot_seven,
                R.drawable.bird_parrot_eight
        };

        String[] names = {"Cat 1", "Cat 2", "Cat 3", "Cat 4" ,"Cat 5" ,"Cat 6","Cat 7",
                "Dog 1","Dog 2","Dog 3","Dog 4","Dog 5",
                "Deer 1","Deer 2","Deer 3","Deer 4",
                "Nils 1 (med jr)", "Nils 2", "Nils 3",
                " Parrot 1"," Parrot 2"," Parrot 3"," Parrot 4"," Parrot 5"," Parrot 6"," Parrot 7"," Parrot 8"};

        for (int i = 0; i < images.length; i++) {

            Animal current = new Animal();
            current.setName(names[i]);
            current.setImageId(images[i]);
            data.add(current);
        }

        return data;
    }
}
