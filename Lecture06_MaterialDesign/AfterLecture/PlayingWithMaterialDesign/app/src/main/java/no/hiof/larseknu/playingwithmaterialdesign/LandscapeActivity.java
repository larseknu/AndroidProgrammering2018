package no.hiof.larseknu.playingwithmaterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import no.hiof.larseknu.playingwithmaterialdesign.adapter.LandscapeRecyclerAdapter;
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape;

public class LandscapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);

        // Sets the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.landscapes);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setLogo(R.drawable.logo_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpRecycleView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    private void setUpRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.landscapeRecycleView);

        // Set our own adapter to be used in the RecycleView, and sends it the data
        recyclerView.setAdapter(new LandscapeRecyclerAdapter(this, Landscape.getData()));

        // Sets the layoutmanager we want to use
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
