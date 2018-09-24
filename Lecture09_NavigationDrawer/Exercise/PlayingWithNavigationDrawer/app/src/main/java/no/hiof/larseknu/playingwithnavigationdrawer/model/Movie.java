package no.hiof.larseknu.playingwithnavigationdrawer.model;

import java.util.ArrayList;
import java.util.List;

import no.hiof.larseknu.playingwithnavigationdrawer.R;

public class Movie {

    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<Movie> getData() {

        List<Movie> data = new ArrayList<>();

        int[] posters = {
                R.drawable.captain_america_civil_war,
                R.drawable.donnie_darko,
                R.drawable.iron_man_3,
                R.drawable.spirited_away,
                R.drawable.star_wars_the_force_awakens,
                R.drawable.the_hobbit,
                R.drawable.up,
                R.drawable.pulp_fiction
        };

        String[] titles = {"Captain America Civil War",
                "Donnie Darko",
                "Iron Man 3",
                "Spirited Away",
                "Star Wars The Force Awakens",
                "The Hobbit",
                "Up",
                "Pulp Fiction"};

        for (int i = 0; i < posters.length; i++) {

            Movie current = new Movie();
            current.setTitle(titles[i]);
            current.setImageId(posters[i]);
            data.add(current);
        }

        return data;
    }
}
