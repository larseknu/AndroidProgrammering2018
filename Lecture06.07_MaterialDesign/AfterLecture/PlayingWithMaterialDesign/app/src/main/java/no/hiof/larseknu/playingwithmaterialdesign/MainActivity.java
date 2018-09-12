package no.hiof.larseknu.playingwithmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Material Design");
        toolbar.setSubtitle("Spaaace");

        setSupportActionBar(toolbar);

        editText = findViewById(R.id.editText);

        //toolbar.setLogo(R.drawable.ic_launcher_foreground);
        //toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);

        // Sets a menu to the toolbar
        //toolbar.inflateMenu(R.menu.menu_main);

        // Sets the activity to handle the clicks on the menu
        //toolbar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Define the listener for expanding/collapsing a MenuItem
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                Toast.makeText(MainActivity.this, "Collapsing", Toast.LENGTH_SHORT).show();
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                Toast.makeText(MainActivity.this, "Expanding", Toast.LENGTH_SHORT).show();
                return true;  // Return true to expand action view
            }
        };

        // Get the MenuItem for the action item
        final MenuItem actionMenuItem = menu.findItem(R.id.action_search);

        // Assign the listener to that action item
        actionMenuItem.setOnActionExpandListener(expandListener);

        SearchView searchView = (SearchView) actionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(MainActivity.this, "Text submitted: " + s, Toast.LENGTH_SHORT).show();
                actionMenuItem.collapseActionView();
                editText.setText(String.format("Searched: %s", s));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(MainActivity.this, "Text changed " + s, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handles which item in the menu that gets clicked
        switch (item.getItemId()) {
            case R.id.landscape:
                startActivity(new Intent(this, LandscapeActivity.class));
                break;
            case R.id.animals:
                Toast.makeText(this, "Animals clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.colors:
                Toast.makeText(this, "Colors clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

}
