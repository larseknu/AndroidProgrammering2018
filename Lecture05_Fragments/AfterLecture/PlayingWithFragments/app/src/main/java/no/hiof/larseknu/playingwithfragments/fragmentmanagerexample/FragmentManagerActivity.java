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

    public void addJoyFragment(View view) {
        JoyFragment joyFragment = new JoyFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.content, joyFragment, "Joy");
        fragmentTransaction.addToBackStack("Joy");
        fragmentTransaction.commit();
    }

    public void addSadnessFragment(View view) {
        SadnessFragment sadnessFragment = new SadnessFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.content, sadnessFragment, "Sadness");
        fragmentTransaction.addToBackStack("Sadness");
        fragmentTransaction.commit();
    }

    public void removeJoyFragment(View view) {
        Fragment fragment = fragmentManager.findFragmentByTag("Joy");

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    public void removeSadnessFragment(View view) {
        Fragment fragment = fragmentManager.findFragmentByTag("Sadness");

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    public void replaceWithJoyFragment(View view) {
        JoyFragment happyFragment = new JoyFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, happyFragment, "HappyReplaced");

        fragmentTransaction.commit();
    }

    public void replaceWithSadnessFragment(View view) {
        SadnessFragment sadFragment = new SadnessFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, sadFragment, "SadReplaced");

        fragmentTransaction.commit();
    }
}
