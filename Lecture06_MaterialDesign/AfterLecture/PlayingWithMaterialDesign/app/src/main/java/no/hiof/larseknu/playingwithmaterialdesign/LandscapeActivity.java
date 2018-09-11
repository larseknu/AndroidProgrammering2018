package no.hiof.larseknu.playingwithmaterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import no.hiof.larseknu.playingwithmaterialdesign.adapter.LandscapeRecyclerAdapter;
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape;

public class LandscapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);

        // Sets the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.beautiful_landscapes);

        setUpRecycleView();
    }

    private void setUpRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.landscapeRecycleView);

        // Set our own adapter to be used in the RecycleView, and sends it the data
        recyclerView.setAdapter(new LandscapeRecyclerAdapter(this, Landscape.getData()));

        // Sets the layoutmanager we want to use
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
