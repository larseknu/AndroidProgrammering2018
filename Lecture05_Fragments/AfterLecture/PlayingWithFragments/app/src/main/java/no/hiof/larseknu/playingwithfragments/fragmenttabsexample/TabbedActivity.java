package no.hiof.larseknu.playingwithfragments.fragmenttabsexample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import no.hiof.larseknu.playingwithfragments.R;
import no.hiof.larseknu.playingwithfragments.fragmentdetailexample.MovieListFragment;

public class TabbedActivity extends AppCompatActivity implements MovieListFragment.OnMovieFragmentInteractionListener {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        setTitle("FragmentTabbedExample");

        ViewPager viewPager = findViewById(R.id.viewpager);
        setUpViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setUpTabIcons();
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DiscoverFragment(), "Discover");
        adapter.addFragment(new MovieListFragment(), "Collection");
        adapter.addFragment(new WatchlistFragment(), "Watchlist");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onMovieSelected(int id) {
        Toast.makeText(this, "Movie " + id + " clicked", Toast.LENGTH_SHORT).show();
    }

    private void setUpTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_visibility_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_collections_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_binoculars_white_24dp);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return fragmentTitleList.get(position);
            return null;
        }
    }


}
