package no.hiof.larseknu.playingwithfragments.fragmentmanagerexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import no.hiof.larseknu.playingwithfragments.R;

public class FragmentManagerActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);

        setTitle(R.string.title_activity_fragment_manager);

        fragmentManager = getSupportFragmentManager();
    }
}
