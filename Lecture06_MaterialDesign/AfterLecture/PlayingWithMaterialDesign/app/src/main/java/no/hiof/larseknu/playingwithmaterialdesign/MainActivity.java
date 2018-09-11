package no.hiof.larseknu.playingwithmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        toolbar.setTitle("Welcome!");
        toolbar.setSubtitle("Students");

        //toolbar.setLogo(R.drawable.ic_launcher_foreground);
        //toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);

        // Sets a menu to the toolbar
        toolbar.inflateMenu(R.menu.menu_main);
        // Sets the activity to handle the clicks on the menu
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Handles which item in the menu that gets clicked
        switch (menuItem.getItemId()) {
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
