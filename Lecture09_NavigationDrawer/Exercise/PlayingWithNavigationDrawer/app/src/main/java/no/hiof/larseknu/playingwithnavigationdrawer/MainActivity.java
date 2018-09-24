package no.hiof.larseknu.playingwithnavigationdrawer;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import no.hiof.larseknu.playingwithnavigationdrawer.fragment.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationDrawerFragment navigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello! I'm Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("Kill", snackBarClickListener)
                        .show();
            }
        });

        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        navigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentNavigationDrawer);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        navigationDrawerFragment.setUpDrawer(drawerLayout, toolbar);
    }


    private  View.OnClickListener snackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "You killed Snackbar, you bastard!", Snackbar.LENGTH_LONG).show();
        }
    };

}
