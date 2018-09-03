package no.hiof.larseknu.playingwithfragments.fragmentdetailexample;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import no.hiof.larseknu.playingwithfragments.R;

public class MovieListActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setTitle("FragmentDetailExample");
    }
}
