package no.hiof.larseknu.playingwithnavigationdrawer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import no.hiof.larseknu.playingwithnavigationdrawer.ListActivity;
import no.hiof.larseknu.playingwithnavigationdrawer.R;


public class NavigationDrawerFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    public NavigationDrawerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container);

        navigationView = view.findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

        return view;
    }

    public void setUpDrawer(DrawerLayout setDrawerLayout, Toolbar toolbar) {
        drawerLayout = setDrawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_shows:
                Toast.makeText(getActivity(), "Shows clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_lists:
                Toast.makeText(getActivity(), "Lists clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ListActivity.class));
                break;
            case R.id.nav_settings:
                Toast.makeText(getActivity(), "Settings clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help_feedback:
                Toast.makeText(getActivity(), "Help clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
