package no.hiof.larseknu.playingwithfragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import no.hiof.larseknu.playingwithfragments.buttonhandlingexample.ButtonHandlingActivity;
import no.hiof.larseknu.playingwithfragments.buttonhandlingexample.ButtonHandlingFragment;
import no.hiof.larseknu.playingwithfragments.fragmentdetailexample.MovieListActivity;
import no.hiof.larseknu.playingwithfragments.fragmentmanagerexample.FragmentManagerActivity;
import no.hiof.larseknu.playingwithfragments.fragmenttabsexample.TabbedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startFramentManagerActivity(View view) {
        startActivity(new Intent(this, FragmentManagerActivity.class));
    }

    public void startFramentDetailActivity(View view) {
        startActivity(new Intent(this, MovieListActivity.class));
    }

    public void startFragmentTabsActivity(View view) {
        startActivity(new Intent(this, TabbedActivity.class));
    }

    public void startFramentButtonClickActivity(View view) {
        startActivity(new Intent(this, ButtonHandlingActivity.class));
    }
}
